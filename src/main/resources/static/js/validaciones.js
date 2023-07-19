function controlarLongitudCaracteres(idInput='#run', logitudMax=8, LongitudMin=0){
    var input = $(idInput).val();
    if(input.length > logitudMax){
        console.log("entrando al if");
        var nuevoVal= input.substring(0,logitudMax);
        $(idInput).val(nuevoVal);
    }
}
function controlarSoloNumero(idInput ='#run') {
    var input = $(idInput).val();
    var regex = /^\d*$/;
    if (!regex.test(input)) {
        $(idInput).val( input.replace(/\D/g, ''));
    }
}


// Validadion de solo letras con ÑñéÉ...
function validarSoloTexto(idInput){
    var input = $(idInput).val();
    $(idInput).val(input.replace(/[^A-Za-záéíóúñÑÁÉÍÓÚ ]+/g,''));
}
function codigoVerificador() {
    $("#runTd").each(function (){
        var run = $('#runTd').text();
        var caracter;
        var runInvertido = "";
        var multiplicador=1;
        var resultado=0;
        var formatearARun="";
        for (let i = run.length - 1; i >= 0; i--) {
            caracter = run.charAt(i);
            runInvertido += caracter;
            if (multiplicador < 7) {
                multiplicador++;
                resultado += multiplicador * caracter;
                console.log(multiplicador + " * " + caracter);

            } else {
                multiplicador = 2;
                console.log(multiplicador + " * " + caracter);
                resultado += multiplicador * caracter;
            }
            if (run.length === 8) {
                switch (i+1) {
                    case 3:
                    case 6:
                        formatearARun = "."+ caracter + formatearARun ;
                        break;
                    default:
                        formatearARun = caracter + formatearARun;
                        break;
                }
            } else {
                switch (i+1) {
                    case 2:
                    case 5:
                        formatearARun = "."+caracter + formatearARun ;
                        break;
                    default:
                        formatearARun = caracter + formatearARun;
                        break;
                }
                console.log(caracter);
            }
        }
        var resultadoTotal = resultado;
        formatearARun=formatearARun+"-";
        console.log(runInvertido);
        console.log("Suma totalo: "+resultado);
        console.log("formateado: "+formatearARun);
        resultado =  Math.floor(resultado/11);
        console.log("resultado dividiendo /11: "+resultado);
        resultado = resultado *11;
        console.log("resultado multiplicando 11: "+resultado);
        resultadoTotal = resultadoTotal - resultado;
        console.log("resultadoTotal - resultado: "+resultadoTotal);
        switch (resultadoTotal){
            case 10:
                formatearARun +="K";
                break;
            case 11:
                formatearARun +="0";
                break;
            default:
                formatearARun +=resultadoTotal;
                break;
        }
        console.log("Run Completo: "+formatearARun);
        $('#runTd').text(formatearARun);
    });
}




//llamado funciones
$(document).ready(function() {
    // Asignar el evento input al input RUN
    $('#run').on('input', function() {
        controlarLongitudCaracteres("#run", 8);
        controlarSoloNumero("#run");
    });
    $('#nombre').on('input',function (){
       validarSoloTexto('#nombre')
    });
    $('#apellido1').on('input',function (){
        validarSoloTexto('#apellido1')
    });
    $('#apellido2').on('input',function (){
        validarSoloTexto('#apellido2')
    });
});
