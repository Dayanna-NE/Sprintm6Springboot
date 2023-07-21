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

function controlarSoloNumero(idInput='') {
    let input = $(idInput).val();
    $(idInput).val( input.replace(/[^0-9]+/g, ''));
}


// Validadion de solo letras con ÑñéÉ...
function validarSoloTexto(idInput,espacio=true){
    let input = $(idInput).val();
    if (espacio){
    $(idInput).val(input.replace(/[^A-Za-záéíóúñÑÁÉÍÓÚ ]+/g,''));}
    else{
        $(idInput).val(input.replace(/[^A-Za-záéíóúñÑÁÉÍÓÚ]+/g,''));
    }
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
        controlarLongitudCaracteres("#telefono", 12,9);
    });
    $('#nombre').on('input',function (){
       validarSoloTexto('#nombre');
    });
    $('#apellido1').on('input',function (){
        validarSoloTexto('#apellido1',false);
    });
    $('#apellido2').on('input',function (){
        validarSoloTexto('#apellido2',false);
    });





// validando mi lista de trabajadores
    var id_empleador;
    $(".li-empleador").click(function () {
        const checkBox = $(this).find(".form-check-input");
        const label = $(this).find(".form-check-label");
        id_empleador=$(this);
        if (checkBox.prop("checked")) {
            checkBox.prop("checked", true);
            checkBox.toggleClass("bg-dark");
            label.toggleClass("text-light");
            $(this).toggleClass("bg-primary");

        } else {
            checkBox.prop("checked", false);
            checkBox.removeClass("bg-dark");
            label.removeClass("text-light");
            $(this).removeClass("bg-primary");
        }

        const numChecked = $("#ulEmpleador").find(".form-check-input:checked").length;

        if (numChecked > 0) {
            console.log("Al menos un checkbox está activo");
            $('#validoUl').remove("is-invalid");
            $('#validoUl').toggleClass("is-valid");
            $("#ulEmpleador").removeClass("border-danger");
            $("#ulEmpleador").toggleClass("border-success");
            $("#validHidenCB").removeAttr("required");
            $(".form-check-input").removeAttr("required");
        } else {
            console.log("Ningún checkbox está activo");
            $('#validoUl').removeClass("is-valid");
            $('#validoUl').toggleClass("is-invalid");
            $("#ulEmpleador").removeClass("border-success");
            $("#ulEmpleador").toggleClass("border-danger");
            $("#validHidenCB").attr("required", "true");
            $(".form-check-input").attr("required", "true");
        }

    });
    $(".form-check-input").click(function (){
        const label = $('#' + $(this).attr("for"));
        const li = $("#Li" + $(this).attr("id"));
        if($(this).prop("checked")===false){
            $(this).toggleClass("bg-dark");
            label.toggleClass("text-light");
            li.toggleClass("bg-primary");
        }else{
            $(this).removeClass("bg-dark");
            label.removeClass("text-light");
            li.removeClass("bg-primary");
        }
    });


});
