const inputValor = document.getElementById("valor");
const selectOrigem = document.getElementById("moeda-origem");
const selectDestino = document.getElementById("moeda-destino");
const resposta = document.querySelector(".resposta");

async function converter() {
  const valor = parseFloat(inputValor.value);

  if (isNaN(valor) || valor <= 0) {
    resposta.innerHTML = "";
    return;
  }

  const moedaOrigem = selectOrigem.value;
  const moedaDestino = selectDestino.value;

  if (moedaOrigem === moedaDestino) {
    resposta.innerHTML = `
      <div class="resultado-valor">${valor.toFixed(2)} ${moedaDestino}</div>
    `;
    return;
  }

  const taxa = await buscarTaxa(moedaOrigem, moedaDestino);

  if (!taxa) {
    resposta.innerHTML = "❌ Erro ao obter cotação.";
    return;
  }

  const convertido = valor * taxa;

  resposta.innerHTML = `
    <div class="resultado-valor">${convertido.toFixed(2)} ${moedaDestino}</div>
    <div class="resultado-taxa">
      Taxa: 1 ${moedaOrigem} = ${Number(taxa).toFixed(6)} ${moedaDestino}
    </div>
  `;
}

inputValor.addEventListener("input", converter);
selectOrigem.addEventListener("change", converter);
selectDestino.addEventListener("change", converter);


// CONVERSOR DE IDIOMA

const translations = {
  pt: {
    title: "Conversor de Moedas",
    subtitle: "Converta valores entre moedas do mundo todo",
    origin: "Moeda de origem",
    destination: "Moeda de destino",
    value: "Valor",
    placeholder: "Digite o valor",
    other: "Outras moedas",
    about: "Sobre"
  },

  en: {
    title: "Currency Converter",
    subtitle: "Convert values between currencies worldwide",
    origin: "From currency",
    destination: "To currency",
    value: "Amount",
    placeholder: "Enter value",
    other: "Other currencies",
    about: "About"
  },

  es: {
    title: "Conversor de Monedas",
    subtitle: "Convierte valores entre monedas del mundo",
    origin: "Moneda de origen",
    destination: "Moneda de destino",
    value: "Valor",
    placeholder: "Ingrese el valor",
    other: "Otras monedas",
    about: "Acerca de"
  }
};

// ===== Elementos da tela =====
const title = document.querySelector(".converter-header h2");
const subtitle = document.querySelector(".converter-header p");

const labelOrigin = document.querySelector("label[for='moeda-origem']");
const labelDestination = document.querySelector("label[for='moeda-destino']");
const labelValue = document.querySelector("label[for='valor']");

const inputValue = document.getElementById("valor");

const menuLinks = document.querySelectorAll(".menu a");

// ===== Função que troca idioma =====
function setLanguage(lang) {
  const t = translations[lang];

  title.textContent = t.title;
  subtitle.textContent = t.subtitle;
  labelOrigin.textContent = t.origin;
  labelDestination.textContent = t.destination;
  labelValue.textContent = t.value;
  inputValue.placeholder = t.placeholder;

  menuLinks[0].textContent = t.other;
  menuLinks[1].textContent = t.about;

  // Salva idioma escolhido
  localStorage.setItem("lang", lang);
}

// ===== Botões =====
document.querySelectorAll(".btn-idiomas button").forEach(btn => {
  btn.addEventListener("click", () => {
    const lang = btn.textContent.toLowerCase();
    setLanguage(lang);
  });
});

// ===== Carrega idioma salvo =====
const savedLang = localStorage.getItem("lang") || "pt";
setLanguage(savedLang);
