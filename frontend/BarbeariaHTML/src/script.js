
        // Configuração de horários de funcionamento (fechado sexta e sábado)
        const businessHours = {
            "Segunda-feira": { open: "06:00", close: "17:00" },
            "Terça-feira": { open: "06:00", close: "17:00" },
            "Quarta-feira": { open: "06:00", close: "17:00" },
            "Quinta-feira": { open: "06:00", close: "17:00" },
            "Sexta-feira": { open: null, close: null }, // Fechado
            "Sábado": { open: null, close: null }, // Fechado
            "Domingo": { open: "08:00", close: "14:00" }
        };
        
        // Dias da semana em português
        const weekdays = ["Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"];
        
        // Serviços disponíveis
        const services = [
            {
                id: 1,
                name: "Corte Social",
                price: 30.00,
                description: "Corte clássico e profissional",
                icon: "fas fa-cut"
            },
            {
                id: 2,
                name: "Corte Degradê",
                price: 35.00,
                description: "Corte moderno com degradê",
                icon: "fas fa-layer-group"
            },
            {
                id: 3,
                name: "Corte + Barba",
                price: 50.00,
                description: "Corte completo com barba",
                icon: "fas fa-user-check"
            },
            {
                id: 4,
                name: "Barba Completa",
                price: 25.00,
                description: "Aparar e modelar a barba",
                icon: "fas fa-air-freshener"
            },
            {
                id: 5,
                name: "Sobrancelha",
                price: 15.00,
                description: "Design e modelagem de sobrancelhas",
                icon: "fas fa-eye"
            },
            {
                id: 6,
                name: "Pigmentação",
                price: 40.00,
                description: "Técnica de realce da barba",
                icon: "fas fa-palette"
            }
        ];
        
        // Estado da aplicação
        const state = {
            selectedServices: [],
            paymentMethod: null,
            customerData: {}
        };
        
        // Função para verificar se o estabelecimento está aberto
        function checkBusinessStatus() {
            const now = new Date();
            const currentDay = weekdays[now.getDay()];
            const currentTime = now.getHours().toString().padStart(2, '0') + ':' + 
                                now.getMinutes().toString().padStart(2, '0');
            
            const todayHours = businessHours[currentDay];
            
            // Verificar se está fechado no dia atual
            if (!todayHours.open || !todayHours.close) {
                return { isOpen: false, message: "Fechado hoje" };
            }
            
            // Verificar se está dentro do horário de funcionamento
            if (currentTime >= todayHours.open && currentTime <= todayHours.close) {
                return { 
                    isOpen: true, 
                    message: `Aberto - Fecha às ${todayHours.close}` 
                };
            } else if (currentTime < todayHours.open) {
                return { 
                    isOpen: false, 
                    message: `Fechado - Abre ${getNextOpenDay(now)}` 
                };
            } else {
                return { 
                    isOpen: false, 
                    message: `Fechado - Abre ${getNextOpenDay(now)}` 
                };
            }
        }
        
        // Função para obter o próximo dia de funcionamento
        function getNextOpenDay(currentDate) {
            let nextDay = new Date(currentDate);
            nextDay.setDate(nextDay.getDate() + 1);
            
            for (let i = 0; i < 7; i++) {
                const dayName = weekdays[nextDay.getDay()];
                const dayHours = businessHours[dayName];
                
                if (dayHours.open && dayHours.close) {
                    return `${dayName} às ${dayHours.open}`;
                }
                
                nextDay.setDate(nextDay.getDate() + 1);
            }
            
            return "em breve";
        }
        
        // Atualizar o status na página
        function updateStatusDisplay() {
            const status = checkBusinessStatus();
            const statusIndicator = document.getElementById('statusIndicator');
            const statusIcon = document.getElementById('statusIcon');
            const statusText = document.getElementById('statusText');
            const btnAgendar = document.getElementById('btnAgendar');
            
            if (status.isOpen) {
                statusIndicator.className = 'status-indicator status-open';
                statusIcon.className = 'status-icon fas fa-door-open';
                statusText.textContent = status.message;
                btnAgendar.disabled = false;
            } else {
                statusIndicator.className = 'status-indicator status-closed';
                statusIcon.className = 'status-icon fas fa-door-closed';
                statusText.textContent = status.message;
                btnAgendar.disabled = true;
            }
        }
        
        // Preencher a lista de horários de funcionamento
        function populateHoursList() {
            const hoursList = document.getElementById('hoursList');
            const today = new Date();
            const currentDayName = weekdays[today.getDay()];
            
            for (const [day, hours] of Object.entries(businessHours)) {
                const listItem = document.createElement('li');
                
                if (day === currentDayName) {
                    listItem.className = 'current-day';
                }
                
                if (!hours.open || !hours.close) {
                    listItem.classList.add('closed-day');
                }
                
                const daySpan = document.createElement('span');
                daySpan.textContent = day;
                
                const hoursSpan = document.createElement('span');
                if (hours.open && hours.close) {
                    hoursSpan.textContent = `${hours.open} - ${hours.close}`;
                } else {
                    hoursSpan.textContent = "Fechado";
                    hoursSpan.style.color = "#f44336";
                }
                
                listItem.appendChild(daySpan);
                listItem.appendChild(hoursSpan);
                hoursList.appendChild(listItem);
            }
        }
        
        // Preencher a grade de serviços
        function populateServicesGrid() {
            const servicesGrid = document.getElementById('servicesGrid');
            
            services.forEach(service => {
                const serviceCard = document.createElement('div');
                serviceCard.className = 'service-card';
                serviceCard.dataset.id = service.id;
                
                serviceCard.innerHTML = `
                    <div class="service-icon">
                        <i class="${service.icon}"></i>
                    </div>
                    <div class="service-name">${service.name}</div>
                    <div class="service-price">R$ ${service.price.toFixed(2)}</div>
                    <div class="service-description">${service.description}</div>
                `;
                
                serviceCard.addEventListener('click', () => {
                    toggleServiceSelection(service.id, serviceCard);
                });
                
                servicesGrid.appendChild(serviceCard);
            });
        }
        
        // Alternar seleção de serviço
        function toggleServiceSelection(serviceId, serviceCard) {
            const index = state.selectedServices.findIndex(s => s.id === serviceId);
            
            if (index === -1) {
                // Adicionar serviço
                const service = services.find(s => s.id === serviceId);
                state.selectedServices.push(service);
                serviceCard.classList.add('selected');
            } else {
                // Remover serviço
                state.selectedServices.splice(index, 1);
                serviceCard.classList.remove('selected');
            }
            
            // Atualizar estado do botão
            document.getElementById('toCart').disabled = state.selectedServices.length === 0;
        }
        
        // Atualizar carrinho
        function updateCart() {
            const cartItems = document.getElementById('cartItems');
            const cartTotal = document.getElementById('cartTotal');
            
            // Limpar carrinho
            cartItems.innerHTML = '';
            
            // Adicionar itens selecionados
            state.selectedServices.forEach(service => {
                const cartItem = document.createElement('div');
                cartItem.className = 'cart-item';
                cartItem.innerHTML = `
                    <div class="cart-item-name">${service.name}</div>
                    <div class="cart-item-price">R$ ${service.price.toFixed(2)}</div>
                `;
                cartItems.appendChild(cartItem);
            });
            
            // Calcular total
            const total = state.selectedServices.reduce((sum, service) => sum + service.price, 0);
            cartTotal.textContent = `R$ ${total.toFixed(2)}`;
        }
        
        // Navegar entre telas
        function navigateTo(screenId) {
            // Ocultar todas as telas
            document.querySelectorAll('.spa-screen').forEach(screen => {
                screen.classList.remove('active');
            });
            
            // Mostrar a tela desejada
            if (screenId) {
                document.getElementById(screenId).classList.add('active');
            }
        }
        
        // Inicializar a página
        document.addEventListener('DOMContentLoaded', function() {
            updateStatusDisplay();
            populateHoursList();
            populateServicesGrid();
            
            // Atualizar o status a cada minuto
            setInterval(updateStatusDisplay, 60000);
            
            // Formatação automática do telefone
            document.getElementById('telefone').addEventListener('input', function(e) {
                let value = e.target.value.replace(/\D/g, '');
                
                if (value.length <= 11) {
                    if (value.length <= 2) {
                        value = value.replace(/^(\d{0,2})/, '($1');
                    } else if (value.length <= 6) {
                        value = value.replace(/^(\d{2})(\d{0,4})/, '($1) $2');
                    } else if (value.length <= 10) {
                        value = value.replace(/^(\d{2})(\d{4})(\d{0,4})/, '($1) $2-$3');
                    } else {
                        value = value.replace(/^(\d{2})(\d{5})(\d{0,4})/, '($1) $2-$3');
                    }
                }
                
                e.target.value = value;
            });
            
            // Definir data mínima como hoje e bloquear sextas e sábados
            const today = new Date();
            const minDate = new Date(today);
            minDate.setDate(today.getDate() + 1); // Não permitir agendamento para hoje
            
            // Formatar para YYYY-MM-DD
            const minDateString = minDate.toISOString().split('T')[0];
            document.getElementById('data').setAttribute('min', minDateString);
            
            // Bloquear seleção de sextas e sábados
            document.getElementById('data').addEventListener('change', function() {
                const selectedDate = new Date(this.value);
                const dayOfWeek = selectedDate.getDay();
                
                // 5 = sexta, 6 = sábado
                if (dayOfWeek === 5 || dayOfWeek === 6) {
                    alert('Não é possível agendar para sextas ou sábados. Nossa barbearia está fechada nestes dias.');
                    this.value = '';
                }
            });
            
            // Navegação entre telas
            document.getElementById('btnAgendar').addEventListener('click', () => {
                navigateTo('servicesScreen');
            });
            
            document.getElementById('backToHome').addEventListener('click', () => {
                navigateTo('');
            });
            
            document.getElementById('toCart').addEventListener('click', () => {
                updateCart();
                navigateTo('cartScreen');
            });
            
            document.getElementById('backToServices').addEventListener('click', () => {
                navigateTo('servicesScreen');
            });
            
            document.getElementById('toPayment').addEventListener('click', () => {
                navigateTo('paymentScreen');
            });
            
            document.getElementById('backToCart').addEventListener('click', () => {
                navigateTo('cartScreen');
            });
            
            document.getElementById('backToPayment').addEventListener('click', () => {
                navigateTo('paymentScreen');
            });
            
            document.getElementById('newAppointment').addEventListener('click', () => {
                // Resetar estado
                state.selectedServices = [];
                state.paymentMethod = null;
                state.customerData = {};
                
                // Resetar seleções visuais
                document.querySelectorAll('.service-card').forEach(card => {
                    card.classList.remove('selected');
                });
                
                document.querySelectorAll('.payment-option').forEach(option => {
                    option.classList.remove('selected');
                });
                
                document.getElementById('agendamentoForm').reset();
                document.getElementById('toCart').disabled = true;
                document.getElementById('toScheduling').disabled = true;
                
                navigateTo('');
            });
            
            // Seleção de método de pagamento
            document.querySelectorAll('.payment-option').forEach(option => {
                option.addEventListener('click', () => {
                    document.querySelectorAll('.payment-option').forEach(opt => {
                        opt.classList.remove('selected');
                    });
                    
                    option.classList.add('selected');
                    state.paymentMethod = option.dataset.method;
                    document.getElementById('toScheduling').disabled = false;
                });
            });
            
            document.getElementById('toScheduling').addEventListener('click', () => {
                navigateTo('schedulingScreen');
            });
            
            // Manipular envio do formulário
            document.getElementById('agendamentoForm').addEventListener('submit', function(e) {
                e.preventDefault();
                
                // Validação básica
                const nome = document.getElementById('nome').value;
                const data = document.getElementById('data').value;
                const hora = document.getElementById('hora').value;
                const telefone = document.getElementById('telefone').value;
                
                if (!nome || !data || !hora || !telefone) {
                    alert('Por favor, preencha todos os campos!');
                    return;
                }
                
                // Verificar se a data selecionada é sexta ou sábado
                const selectedDate = new Date(data);
                const dayOfWeek = selectedDate.getDay();
                if (dayOfWeek === 5 || dayOfWeek === 6) {
                    alert('Não é possível agendar para sextas ou sábados. Nossa barbearia está fechada nestes dias.');
                    return;
                }
                
                // Salvar dados do cliente
                state.customerData = { nome, data, hora, telefone };
                
                // Formatação da data para exibição
                const dataObj = new Date(data);
                const dataFormatada = dataObj.toLocaleDateString('pt-BR');
                
                // Calcular total
                const total = state.selectedServices.reduce((sum, service) => sum + service.price, 0);
                
                // Preencher detalhes da confirmação
                const confirmationDetails = document.getElementById('confirmationDetails');
                confirmationDetails.innerHTML = `
                    <p><strong>Nome:</strong> ${nome}</p>
                    <p><strong>Data:</strong> ${dataFormatada}</p>
                    <p><strong>Horário:</strong> ${hora}</p>
                    <p><strong>Telefone:</strong> ${telefone}</p>
                    <p><strong>Serviços:</strong> ${state.selectedServices.map(s => s.name).join(', ')}</p>
                    <p><strong>Forma de Pagamento:</strong> ${state.paymentMethod === 'local' ? 'No Local' : 'Online'}</p>
                    <p><strong>Total:</strong> R$ ${total.toFixed(2)}</p>
                `;
                
                navigateTo('confirmationScreen');
            });
        });
