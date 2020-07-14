/*$(document).ready(() => {
	var itemPedido = document.querySelectorAll("#produtos tbody tr");
    for (var i = 0; i < itemPedido.length; i++) {
        itemPedido[i].addEventListener("click", add);
    }
    var busca = document.querySelector("#busca");
    busca.addEventListener("keyup", filtrar);
    
    var troco = 0;
    var btCancela = document.querySelector("#bt-can");
    btCancela.addEventListener("click", cancela);

    var btFinalizar = document.querySelector("#bt-pag");
    btFinalizar.addEventListener("click", finalizar);

    function add(e) {
        var total = 0;
        var tr = e.target.parentElement;
        var td = tr.querySelectorAll("td");
        console.log(tr.innerHTML);
        var codPrato = tr.querySelector("input[name='cod']").value;
        var existe = false;

        var btExclui = document.createElement("img");
        btExclui.src = "icons/remover.png"
        btExclui.id = "exclui";
        btExclui.className = " d-print-none";
        btExclui.addEventListener("click", remove);

        var inpCod = document.createElement("input");
        inpCod.value = codPrato;
        inpCod.name = "id";
        inpCod.type = "hidden";

        var inpQnt = document.createElement("input");
        inpQnt.value = 1;
        inpQnt.name = "qnt";
        inpQnt.className = "col-6";
        inpQnt.type = "number";

        var btMais = document.createElement("span");
        btMais.innerText = "+";
        btMais.className = "btn btn-success rounded-circle d-print-none";
        btMais.id = "mais";
        btMais.addEventListener("click", mais);
        var btMenos = document.createElement("span");
        btMenos.innerText = "-";
        btMenos.className = "btn btn-danger rounded-circle d-print-none";
        btMenos.id = "menos";
        btMenos.addEventListener("click", menos);

        var tbody = document.querySelector("#ped-cli tbody");
        var trCli = document.createElement("tr");
        trCli.id="lancar-linha";
        var tdCli = document.createElement("td");

        tbody.appendChild(trCli);

        tdCli = document.createElement("td");
        trCli.appendChild(tdCli);
        tdCli.appendChild(btExclui);

        tdCli = document.createElement("td");
        trCli.appendChild(tdCli);
        tdCli.innerText = td[2].innerText;
        tdCli.appendChild(inpCod);

        tdCli = document.createElement("td");
        tdCli.innerText = td[3].innerText;
        trCli.appendChild(tdCli);

        tdCli = document.createElement("td");
        trCli.appendChild(tdCli);
        tdCli.appendChild(btMenos);
        tdCli.appendChild(inpQnt);
        tdCli.appendChild(btMais);

        tdCli = document.createElement("td");
        tdCli.id = "subtotal";
        tdCli.className = "d-print-none";
        tdCli.innerText = td[3].innerText;
        trCli.appendChild(tdCli);
        calcula();
        valida();
    }
    function remove(e) {
        e.target.closest("tr").remove();
        calcula();
        valida();
    }
    function menos(e) {
        var inp = e.target.closest("td").querySelector("input");

        if (inp.value > 1) {
            inp.value--;
        }

        var td = e.target.closest("tr").querySelectorAll("td");
        var valor = td[3].innerText;
        var subtotal = td[4];
        var tot = inp.value * valor;
        subtotal.innerHTML = tot;
        calcula();
    }
    function mais(e) {
        var inp = e.target.closest("td").querySelector("input");
        var calc = 0;
        inp.value++;

        var td = e.target.closest("tr").querySelectorAll("td");
        var valor = td[2].innerText;
        var subtotal = td[4];
        var tot = inp.value * valor;
        subtotal.innerHTML = tot;

        calcula();
    }
    function calcula() {
        total = 0;
        var subtotal = document.querySelectorAll("#subtotal");
        var tdTotal = document.querySelector("#total");
        for (var i = 0; i < subtotal.length; i++) {
            total += parseFloat(subtotal[i].innerText);
        }
        tdTotal.innerText = "";
        tdTotal.innerText = total;
      
    }
    function cancela(){
        var item = document.querySelectorAll("#ped-cli tbody #lancar-linha");
        for (var i = 0; i < item.length; i++) {
    		item[i].remove();
        }
    	calcula();
    }
    function valida(){
        var btFinaliza = document.querySelector("#extrato form button");
    	var linha = document.querySelectorAll("#lancar-linha");
    	console.log(linha.length);
    	if(linha.length == 0){
    		btFinaliza.disabled =false;
        	console.log("falso");
    	}else{
    		btFinaliza.disabled =true;
        	console.log("verdade");
    	}
    }
    function filtrar() {
        var lista = [];
        for (var i = 0; i < itemPedido.length; i++) {
            var td = itemPedido[i].querySelectorAll("td");
            if (!td[0].innerText.toUpperCase().includes(busca.value.toUpperCase())) {
                itemPedido[i].style.display = "none";
            } else {
                lista.push(itemPedido[i]);
            }
            if (!td[2].innerText.toUpperCase().includes(busca.value.toUpperCase())) {
                itemPedido[i].style.display = "none";
            } else {
                lista.push(itemPedido[i]);
            }
        }

        for (let i = 0; i < lista.length; i++) {
            lista[i].style.display = "";
        }
    }
    $(function () {
        $("[data-toggle='popover']").popover();
    });
    //var btImprime = document.querySelector("#bt-com");
    //btImprime.addEventListener("click", imprimir);
    /*function imprimir(){
    	var header = document.querySelector('head').innerHTML;
    	var conteudo = document.getElementById('extrato').innerHTML;
        tela_impressao = window.open('about:blank');
	    tela_impressao.document.write(header+conteudo);
	    tela_impressao.window.print();
	    tela_impressao.window.close();
    }

});*/