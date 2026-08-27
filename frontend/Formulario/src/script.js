// src/animations.js

document.addEventListener('DOMContentLoaded', function() {
    // Inicializa GSAP
    gsap.registerPlugin(ScrollTrigger);
    
    // Animação de entrada da página
    const tl = gsap.timeline();
    
    // Animação do header
    tl.from('header', {
        duration: 1,
        y: -50,
        opacity: 0,
        ease: 'power3.out'
    })
    .from('main', {
        duration: 1,
        y: 50,
        opacity: 0,
        ease: 'power3.out'
    }, '-=0.5');
    
    // Animação das seções do formulário
    const sections = gsap.utils.toArray('.form-section');
    
    sections.forEach((section, index) => {
        gsap.to(section, {
            opacity: 1,
            y: 0,
            duration: 0.8,
            ease: 'back.out(1.7)',
            delay: 0.2 * index,
            scrollTrigger: {
                trigger: section,
                start: 'top 80%',
                toggleActions: 'play none none reverse'
            }
        });
    });
    
    // Animação dos botões
    gsap.from('.button-container', {
        duration: 1,
        scale: 0.8,
        opacity: 0,
        delay: 1,
        ease: 'elastic.out(1, 0.5)'
    });
    
    // Animação nos inputs quando focados
    const inputs = document.querySelectorAll('.form-input');
    
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            gsap.to(this, {
                duration: 0.3,
                scale: 1.02,
                boxShadow: '0 10px 20px rgba(52, 152, 219, 0.3)',
                ease: 'power2.out'
            });
        });
        
        input.addEventListener('blur', function() {
            gsap.to(this, {
                duration: 0.3,
                scale: 1,
                boxShadow: '0 0 0 3px rgba(52, 152, 219, 0.2)',
                ease: 'power2.out'
            });
        });
    });
    
    // Animação nos radio buttons e checkboxes
    const radios = document.querySelectorAll('input[type="radio"], input[type="checkbox"]');
    
    radios.forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.checked) {
                const label = document.querySelector(`label[for="${this.id}"]`);
                gsap.fromTo(label,
                    { scale: 0.8, color: '#3498db' },
                    {
                        duration: 0.5,
                        scale: 1,
                        color: '#2c3e50',
                        ease: 'elastic.out(1, 0.5)'
                    }
                );
            }
        });
    });
    
    // Animação nos botões de submit e reset
    const submitBtn = document.querySelector('.submit-btn');
    const resetBtn = document.querySelector('.reset-btn');
    const formFeedback = document.querySelector('.form-feedback');
    
    submitBtn.addEventListener('click', function(e) {
        const form = document.querySelector('form');
        
        if (form.checkValidity()) {
            e.preventDefault(); // Remova isso em produção
            
            // Animação de loading
            gsap.to(this, {
                duration: 0.3,
                scale: 0.95,
                backgroundColor: '#2ecc71',
                ease: 'power2.inOut'
            });
            
            // Animação do ícone
            gsap.to('.btn-icon', {
                duration: 0.5,
                rotation: 360,
                ease: 'power2.out'
            });
            
            // Feedback visual
            gsap.to(formFeedback, {
                duration: 0.5,
                opacity: 1,
                scale: 1,
                backgroundColor: '#2ecc71',
                onStart: () => {
                    formFeedback.textContent = 'Formulário enviado com sucesso!';
                    formFeedback.classList.add('show');
                }
            });
            
            // Reset após animação
            setTimeout(() => {
                gsap.to(this, {
                    duration: 0.3,
                    scale: 1,
                    backgroundColor: '#3498db',
                    ease: 'power2.out'
                });
            }, 1000);
        } else {
            // Animação de erro
            gsap.to(form, {
                duration: 0.5,
                x: [0, 10, -10, 0],
                ease: 'power2.out'
            });
            
            gsap.to(formFeedback, {
                duration: 0.5,
                opacity: 1,
                scale: 1,
                backgroundColor: '#e74c3c',
                onStart: () => {
                    formFeedback.textContent = 'Por favor, preencha todos os campos obrigatórios!';
                    formFeedback.classList.add('show');
                }
            });
        }
    });
    
    resetBtn.addEventListener('click', function() {
        // Animação de reset
        gsap.to('form', {
            duration: 0.8,
            opacity: 0.5,
            scale: 0.98,
            ease: 'power2.out',
            onComplete: () => {
                gsap.to('form', {
                    duration: 0.8,
                    opacity: 1,
                    scale: 1
                });
            }
        });
        
        // Reset feedback
        gsap.to(formFeedback, {
            duration: 0.3,
            opacity: 0,
            scale: 0.9,
            onComplete: () => {
                formFeedback.classList.remove('show');
            }
        });
    });
    
    // Animação ao passar o mouse nos fieldsets
    sections.forEach(section => {
        section.addEventListener('mouseenter', function() {
            gsap.to(this, {
                duration: 0.3,
                scale: 1.02,
                boxShadow: '0 15px 30px rgba(0, 0, 0, 0.15)',
                ease: 'power2.out'
            });
        });
        
        section.addEventListener('mouseleave', function() {
            gsap.to(this, {
                duration: 0.3,
                scale: 1,
                boxShadow: '0 5px 15px rgba(0, 0, 0, 0.1)',
                ease: 'power2.out'
            });
        });
    });
    
    // Animação de scroll progress (opcional)
    const progressBar = document.createElement('div');
    progressBar.className = 'form-progress';
    document.body.appendChild(progressBar);
    
    window.addEventListener('scroll', function() {
        const winScroll = document.body.scrollTop || document.documentElement.scrollTop;
        const height = document.documentElement.scrollHeight - document.documentElement.clientHeight;
        const scrolled = (winScroll / height) * 100;
        
        gsap.to(progressBar, {
            duration: 0.5,
            width: `${scrolled}%`,
            ease: 'power2.out'
        });
    });
    
    // Efeito de digitação no título (opcional)
    const title = document.querySelector('header h1');
    const originalText = title.textContent;
    title.textContent = '';
    
    let i = 0;
    function typeWriter() {
        if (i < originalText.length) {
            title.textContent += originalText.charAt(i);
            i++;
            setTimeout(typeWriter, 50);
        }
    }
    
    // Inicia a animação de digitação
    setTimeout(typeWriter, 1000);
});