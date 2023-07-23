
//Pedir que inicie mi tabla con Datatable
//var table =new DataTable('#lista');

 $(document).ready(function() {
  //Llamo a la tabla que modificaré

  $('#listaLiquidacion').DataTable({
   language: {
    url: '//cdn.datatables.net/plug-ins/1.13.5/i18n/es-ES.json',
  }});
  $('#lista').DataTable({
   language: {
    url: '//cdn.datatables.net/plug-ins/1.13.5/i18n/es-ES.json',
   },
   // Opciones de configuración de DataTables
   "columnDefs": [
       // Modificar la columna selecionada (targets) con la opción "render"
    {
     "targets": 1, // Índice de la columna, 1 = RUN
     "render": function(data) {
      // "data" es el valor original de la celda
      // "type" es el tipo de renderizado (puede ser 'display', 'filter', 'sort' u 'type')
      // "row" es el objeto de datos completo para la fila actual

      // llamo a mi funcion para formatear el campo :D
      return formatearRun(data);
     }
    }
   ]
  });
  $('#runDisabled').val(formatearRun($('#runDisabled').val()));
 });



//Funcion para formatear RUN
 function formatearRun(data) {

  let run = data;
  let caracter;
  let runInvertido = "";
  let multiplicador = 1;
  let resultado = 0;
  let formatearARun = "";
  for (let i = run.length - 1; i >= 0; i--) {
   caracter = run.charAt(i);
   runInvertido += caracter;
   if (multiplicador < 7) {
    multiplicador++;
    resultado += multiplicador * caracter;

   } else {
    multiplicador = 2;
    resultado += multiplicador * caracter;
   }
   if (run.length === 8) {
    switch (i + 1) {
     case 3:
     case 6:
      formatearARun = "." + caracter + formatearARun;
      break;
     default:
      formatearARun = caracter + formatearARun;
      break;
    }
   } else {
    switch (i + 1) {
     case 2:
     case 5:
      formatearARun = "." + caracter + formatearARun;
      break;
     default:
      formatearARun = caracter + formatearARun;
      break;
    }

   }
  }
  let resultadoTotal = resultado;
  formatearARun = formatearARun + "-";
  resultado = Math.floor(resultado / 11);
  resultado = resultado * 11;
  resultadoTotal = resultadoTotal - resultado;
  resultadoTotal = 11 - resultadoTotal;
  switch (resultadoTotal) {
   case 10:
    formatearARun += "K";
    break;
   case 11:
    formatearARun += "0";
    break;
   default:
    formatearARun += resultadoTotal;
    break;
  }
  return formatearARun;
 }
