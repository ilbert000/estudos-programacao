// API pública e gratuita
const BASE_URL = "https://open.er-api.com/v6/latest/";

async function buscarTaxa(from, to) {
  try {
    const res = await fetch(`${BASE_URL}${from}`);
    const data = await res.json();

    if (data.result !== "success") {
      throw new Error("Erro ao buscar dados da API");
    }

    const rate = data.rates[to];

    if (!rate) {
      throw new Error("Moeda não encontrada");
    }

    return rate;

  } catch (err) {
    console.error("Erro na API:", err);
    return null;
  }
}
