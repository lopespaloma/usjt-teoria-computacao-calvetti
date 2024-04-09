let totalRecebido = 0;

function restaurarCoresPadrao() {
    const botaoA = document.getElementById('doceA');
    const botaoB = document.getElementById('doceB');
    const botaoC = document.getElementById('doceC');

    botaoA.style.backgroundColor = '#595959'; // Cor padrão
    botaoB.style.backgroundColor = '#595959'; // Cor padrão
    botaoC.style.backgroundColor = '#595959'; // Cor padrão
}

function voltarTelaInicial() {

    totalRecebido = 0;

    document.getElementById('totalRecebido').innerText = `R$0,00`;
    document.getElementById('doceSection').style.display = 'none';
    document.getElementById('trocoSection').style.display = 'none';
    document.getElementById('resultado').innerText = '';

     restaurarCoresPadrao();
}


function escolherDoce(doce) {
    let preco = 0;
    switch (doce) {
        case 'A':
            preco = 6;
            break;
        case 'B':
            preco = 7;
            break;
        case 'C':
            preco = 8;
            break;
    }

    const troco = totalRecebido - preco;
    if (troco >= 0) {
        totalRecebido = 0;
        document.getElementById('doceSection').style.display = 'none';
        document.getElementById('trocoSection').style.display = 'block';
        document.getElementById('resultado').innerText = `Retire seu doce! Troco: R$${troco.toFixed(2)}`;

        restaurarCoresPadrao();


        const botaoSelecionado = document.getElementById('doce' + doce);
        botaoSelecionado.style.backgroundColor = '#BFA3FF'; // Cor do doce selecionado
    } else {
        alert('Você não tem dinheiro suficiente para comprar este doce.');
    }
}


function receberNota(nota) {
    if (totalRecebido + nota > 10) {
        alert('Limite de R$ 10,00 atingido.');
        return;
    }
    totalRecebido += nota;
    document.getElementById('totalRecebido').innerText = `R$${totalRecebido.toFixed(2)}`;

    const botaoA = document.getElementById('doceA');
    const botaoB = document.getElementById('doceB');
    const botaoC = document.getElementById('doceC');

    if (totalRecebido >= 6) {
        botaoA.style.backgroundColor = '#5CD5E8';
    } else {
        botaoA.style.backgroundColor = '#595959';
    }

    if (totalRecebido >= 7) {
        botaoB.style.backgroundColor = '#FF9666';
    } else {
        botaoB.style.backgroundColor = '#595959';
    }

    if (totalRecebido >= 8) {
        botaoC.style.backgroundColor = '#FF4A7A';
    } else {
        botaoC.style.backgroundColor = '#595959';
    }

    botaoA.classList.remove('disabled');
    botaoB.classList.remove('disabled');
    botaoC.classList.remove('disabled');

    if (totalRecebido < 6) {
        botaoA.classList.add('disabled');
    }
    if (totalRecebido < 7) {
        botaoB.classList.add('disabled');
    }
    if (totalRecebido < 8) {
        botaoC.classList.add('disabled');
    }
}
