gsap.registerPlugin(ScrollTrigger);


gsap.to(".box",{
  /*tempo de animação*/ duration: 1.5,
  /*animação*/  ease: "bounce.out",
  /**/ 
})

/*criamos uma função*/ const sections = /*pesqui para saber*/ gsap.utils.toArray(".container section");

gsap.to(sections, {
  xPercent: -100 * (sections.length - 1),
  ease: "none",
  scrollTrigger: {
    trigger: ".container",
   /*trava o container*/ pin: true,       
   /*scroll controla animação*/ scrub: 1,
    start: "top top",
   /*define quanto da tela rea usada*/ end: () => "+=" + window.innerWidth * sections.length
  }
});

ScrollTrigger.create({
  trigger: ".meio",
  pin: true,
  pinSpacing: false, // chave da solução
  start: "top top",
  end: "+=100%"
});

gsap.to(".fim", {
  scale: 1,
  ease: "none",
  scrollTrigger: {
    trigger: ".penultima",
    start: "top top",
    end: "+=100%",
    pin: true,
    scrub: true,
  }
});

gsap.fromTo(".pitula",
  {
    rotation: 270
  },
  {
    x: 10,
    y: -350,
    scale: 1.5,
    rotation: 0,
    ease: "none",
    scrollTrigger: {
      trigger: ".pitula",
      containerAnimation: ScrollTrigger.getById("horizontalScroll"),
      start: "+=900",
      end: "+=1400",
      scrub: true,
    }
  }
);

gsap.fromTo(".gua",
  {
    x: 5,
    y: 0,
},{
  x: 700,
  y: 500,
  scale:1.5,
    ease: "none",
    scrollTrigger: {
      trigger: ".pitula",
      containerAnimation: ScrollTrigger.getById("horizontalScroll"),
      start: "+=4000",
      end: "+=2500",
      scrub: true,
      
    }
})

gsap.to(".doisLitao",{
  x: 0,
  y: -400,
  scale:1.5,
    ease: "none",
    scrollTrigger: {
      trigger: ".pitula",
      containerAnimation: ScrollTrigger.getById("horizontalScroll"),
      start: "+=4000",
      end: "+=2500",
      scrub: true,
      
    }
})


gsap.to(".doisL",{
  x: 600,
  y: -800,
  scale:1.5,
    ease: "none",
    scrollTrigger: {
      trigger: ".pitula",
      containerAnimation: ScrollTrigger.getById("horizontalScroll"),
      start: "+=4000",
      end: "+=2500",
      scrub: true,
    }
})

gsap.to(".doisL2",{
  x: -150,
  y: -500,
  scale:1.5,
    ease: "none",
    scrollTrigger: {
      trigger: ".pitula",
      containerAnimation: ScrollTrigger.getById("horizontalScroll"),
      start: "+=4000",
      end: "+=2500",
      scrub: true,
      
    }
})

gsap.fromTo(".pitula2",{
  x: 0,
  y: 100
},
{
  x: -150,
  y: -700,
    ease: "elastic",
    scrollTrigger: {
      trigger: ".pitula",
      containerAnimation: ScrollTrigger.getById("horizontalScroll"),
      start: "+=2000",
      end: "+=2100",
      scrub: true,
    }
})


