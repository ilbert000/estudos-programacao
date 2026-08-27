let list = document.querySelectorAll(".item");
let next = document.getElementById("next");
let prev = document.getElementById("prev");

let count = list.length;
// find current active index (if none, default to 0)
let active = Array.from(list).findIndex(el => el.classList.contains('active'));
if(active === -1) active = 0;

function slide(direction){
    const activeOld = list[active];
    const newIndex = direction === 'next' ? (active >= count - 1 ? 0 : active + 1) : (active <= 0 ? count - 1 : active - 1);
    const incoming = list[newIndex];

    // make incoming visible so we can animate it
    incoming.classList.add('active');

    // set initial positions: incoming off-screen, outgoing at 0
    const fromX = direction === 'next' ? '100%' : '-100%';
    const toX = direction === 'next' ? '-100%' : '100%';

    gsap.set(incoming, {x: fromX, zIndex: 2});
    gsap.set(activeOld, {x: '0%', zIndex: 1});

    // disable buttons to avoid double clicks
    next.disabled = prev.disabled = true;

    const tl = gsap.timeline({
        onComplete: () => {
            // remove old active class
            activeOld.classList.remove('active');
            // clear inline styles from transforms
            gsap.set([activeOld, incoming], {clearProps: 'all'});
            active = newIndex;
            next.disabled = prev.disabled = false;
        }
    });

    tl.to(activeOld, {x: toX, duration: 0.6, ease: 'power2.inOut'}, 0)
      .fromTo(incoming, {x: fromX}, {x: '0%', duration: 0.6, ease: 'power2.inOut'}, 0);
}

next.addEventListener('click', () => slide('next'));
prev.addEventListener('click', () => slide('prev'));

//gsap code

gsap.registerPlugin(ScrollTrigger);

// Infinite carousel for cards (smooth, accessible, draggable)
function initInfiniteCarousel() {
  const container = document.querySelector('.carousel-cards');
  if (!container) return;
  if (container.querySelector('.track')) return; // already initialized

  const group = container.querySelector('.grup');
  if (!group) return;

  // make container focusable
  container.setAttribute('tabindex', '0');

  // build track
  const track = document.createElement('div');
  track.className = 'track';
  // move original group into track and append a clone
  const originalGroup = group;
  const cloneGroup = group.cloneNode(true);
  track.appendChild(originalGroup);
  cloneGroup.setAttribute('aria-hidden', 'true');
  track.appendChild(cloneGroup);
  container.appendChild(track);

  const speed = 120; // px per second
  let tl = null;
  let isRunning = true;

  function createTimelineFrom(startX = 0) {
    if (tl) tl.kill();
    const groupWidth = originalGroup.getBoundingClientRect().width;
    gsap.set(track, {x: startX});
    const remaining = -groupWidth - startX; // remaining pixels to travel to reach -groupWidth
    const duration = Math.max(0.1, Math.abs(remaining) / speed);
    tl = gsap.to(track, {
      x: -groupWidth,
      ease: 'none',
      duration,
      onComplete: () => {
        gsap.set(track, {x: 0});
        // restart from 0 seamlessly
        createTimelineFrom(0);
      }
    });
    isRunning = true;
  }

  function pauseTicker() { if (tl) tl.pause(); isRunning = false; }
  function startTicker() { const cur = gsap.getProperty(track, 'x') || 0; createTimelineFrom(cur); }

  // start
  createTimelineFrom(0);

  // play/pause UI
  const playBtn = document.createElement('button');
  playBtn.id = 'card-play';
  playBtn.className = 'card-nav';
  playBtn.innerHTML = '⏯';
  playBtn.setAttribute('aria-label', 'Pausar autoplay');
  container.appendChild(playBtn);

  playBtn.addEventListener('click', () => {
    if (isRunning) { pauseTicker(); playBtn.innerHTML = '▶'; playBtn.setAttribute('aria-label','Iniciar autoplay'); }
    else { startTicker(); playBtn.innerHTML = '⏯'; playBtn.setAttribute('aria-label','Pausar autoplay'); }
  });

  // pause/resume on hover and focus (keep visible control for keyboard)
  container.addEventListener('mouseenter', () => pauseTicker());
  container.addEventListener('mouseleave', () => { if (isRunning) startTicker(); });
  container.addEventListener('focusin', () => pauseTicker());
  container.addEventListener('focusout', () => { if (isRunning) startTicker(); });

  // keyboard support: space toggles play/pause, arrows nudge one card
  container.addEventListener('keydown', (e) => {
    const card = originalGroup.querySelector('.card');
    const cardWidth = card ? card.getBoundingClientRect().width + 20 : 320;
    if (e.key === ' ' || e.key === 'Spacebar') { e.preventDefault(); playBtn.click(); }
    else if (e.key === 'ArrowRight' || e.key === 'ArrowLeft') {
      e.preventDefault(); pauseTicker();
      const dir = e.key === 'ArrowRight' ? -1 : 1; // Right moves forward (more negative)
      const cur = gsap.getProperty(track, 'x') || 0;
      let newX = cur + dir * cardWidth;
      const groupWidth = originalGroup.getBoundingClientRect().width;
      // normalize
      while (newX <= -groupWidth) newX += groupWidth;
      while (newX > 0) newX -= groupWidth;
      gsap.to(track, { x: newX, duration: 0.45, ease: 'power2.inOut', onComplete: () => { startTicker(); } });
    }
  });

  // pointer drag support
  let isDown = false;
  let startX = 0;
  let startTrackX = 0;
  container.addEventListener('pointerdown', (e) => {
    isDown = true;
    startX = e.clientX;
    startTrackX = gsap.getProperty(track, 'x') || 0;
    pauseTicker();
    try{ container.setPointerCapture(e.pointerId); } catch(err){}
  });

  container.addEventListener('pointermove', (e) => {
    if (!isDown) return;
    const dx = e.clientX - startX;
    gsap.set(track, { x: startTrackX + dx });
  });

  container.addEventListener('pointerup', (e) => {
    if (!isDown) return;
    isDown = false;
    try{ container.releasePointerCapture(e.pointerId); } catch(err){}
    const dx = e.clientX - startX;
    const groupWidth = originalGroup.getBoundingClientRect().width;
    let newX = startTrackX + dx;
    // if significant drag, snap to approx card boundary
    const card = originalGroup.querySelector('.card');
    const cardW = card ? card.getBoundingClientRect().width + 20 : 320;
    if (Math.abs(dx) > cardW / 6) {
      const slides = Math.round(dx / cardW);
      newX = startTrackX + dx;
      // normalize
      while (newX <= -groupWidth) newX += groupWidth;
      while (newX > 0) newX -= groupWidth;
      gsap.set(track, { x: newX });
    } else {
      // small movement: just keep current position normalized
      while (newX <= -groupWidth) newX += groupWidth;
      while (newX > 0) newX -= groupWidth;
      gsap.set(track, { x: newX });
    }
    // resume ticker
    startTicker();
  });

  // update on resize
  let resizeTimer;
  window.addEventListener('resize', () => {
    clearTimeout(resizeTimer);
    resizeTimer = setTimeout(() => {
      // rebuild timeline from current position
      const cur = gsap.getProperty(track, 'x') || 0;
      createTimelineFrom(cur);
    }, 150);
  });

  // expose control
  container._carousel = { play: () => startTicker(), pause: () => pauseTicker() };
}

initInfiniteCarousel();