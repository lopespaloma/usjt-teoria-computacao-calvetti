let totalRecebido = 0;


function receberNota(nota) {
    if (totalRecebido + nota > 10) {
        alert('Limite de R$ 10,00 atingido.');
        return;
    }
    totalRecebido += nota;
    document.getElementById('totalRecebido').innerText = `Total recebido: R$${totalRecebido},00`;
    document.getElementById('doceSection').style.display = 'block';
}

function voltarTelaInicial() {
    document.getElementById('totalRecebido').innerText = `Total recebido: R$0,00`;
    document.getElementById('doceSection').style.display = 'none';
    document.getElementById('trocoSection').style.display = 'none';
    totalRecebido = 0;
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
        document.getElementById('resultado').innerText = `Doce entregue! Obrigado pela sua compra. Troco: R$${troco},00`;
    } else {
        alert('Você não tem dinheiro suficiente para comprar este doce.');
    }

}
