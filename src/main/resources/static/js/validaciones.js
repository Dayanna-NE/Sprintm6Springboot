function controlarLongitudCaracteres(idInput='', logitudMax=12, LongitudMin=0){
    let input = $(idInput).val();
    if (input.length <LongitudMin) {
        $(idInput)[0].setCustomValidity('Ingrese un RUN correcto.');
    } else {
        $(idInput)[0].setCustomValidity('');
    }
    if(input.length > logitudMax){
        console.log("entrando al if");
        var nuevoVal= input.substring(0,logitudMax);
        $(idInput).val(nuevoVal);
    }
}

function controlarSoloNumero(idInput ='#run') {
    let input = $(idInput).val();
    let regex = /^\d*$/;
    if (!regex.test(input)) {
        $(idInput).val( input.replace(/\D/g, ''));
    }
}


// Validadion de solo letras con ÑñéÉ...
function validarSoloTexto(idInput){
    let input = $(idInput).val();
    $(idInput).val(input.replace(/[^A-Za-záéíóúñÑÁÉÍÓÚ ]+/g,''));
}





//llamado funciones
$(document).ready(function() {
    // Asignar el evento input al input RUN
    $('#run').on('input', function() {
        controlarSoloNumero("#run");
        controlarLongitudCaracteres("#run", 8,7);
    });
    $('#telefono').on('input', function() {
        controlarSoloNumero("#telefono");
        controlarLongitudCaracteres("#telefono", 9);
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
