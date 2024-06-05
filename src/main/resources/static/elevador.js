let elevador = document.getElementById('elevador');
let display = document.getElementById('display');
let andares = document.querySelectorAll('.andar');
let elevadorPosicao = 0;

function fecharPortas() {
  elevador.classList.remove('aberto');
  elevador.classList.add('fechado');
  atualizarDisplay(elevadorPosicao, false);
}

function abrirPortas() {
  elevador.classList.remove('fechado');
  elevador.classList.add('aberto');
  atualizarDisplay(elevadorPosicao, true);
}

function atualizarDisplay(andar, estadoPortas) {
  let andarTexto = andar === 0 ? 'Térreo' : `${andar}º`;
  let estadoTexto = estadoPortas ? '| aberto' : '| fechado';
  display.innerText = `${andarTexto} ${estadoTexto}`;
}

function atualizarBotaoSelecionado(andar) {
  andares.forEach(botao => botao.classList.remove('selecionado'));
  const botaoSelecionado = document.querySelector(`.andar[data-andar="${andar}"]`);
  botaoSelecionado.classList.add('selecionado');
}

function moverElevador(andarDestino) {
  fecharPortas();
  atualizarDisplay(elevadorPosicao);
  atualizarBotaoSelecionado(elevadorPosicao);

  let intervalo = setInterval(() => {
    if (elevadorPosicao < andarDestino) {
      elevadorPosicao++;
    } else if (elevadorPosicao > andarDestino) {
      elevadorPosicao--;
    } else {
      clearInterval(intervalo);
      abrirPortas();
      atualizarBotaoSelecionado(andarDestino);
      return;
    }
    atualizarDisplay(elevadorPosicao);
    atualizarBotaoSelecionado(elevadorPosicao);
  }, 1000);
}

function selecionarAndar(event) {
  let andarSelecionado = parseInt(event.target.getAttribute('data-andar'));
  moverElevador(andarSelecionado);
}
andares.forEach(andar => {
  andar.addEventListener('click', selecionarAndar);
});
