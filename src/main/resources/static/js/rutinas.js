
/* La siguiente función se utiliza para visualizar la imagen seleccionada en la
 * página html donde se desea "cargar" utilizando un llamado "ajax"*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah')
                    .attr('src', e.target.result)
                    .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCart(formulario) {
    var idProducto = formulario.elements[0].value;
    var existencias = formulario.elements[1].value;
    var ruta = '/carrito/agregar/' + idProducto;
    if (existencias > 0) {
        $("#resultBlock").load(ruta);
    } else { //Si no hay exietencias, se le dice al suaurio
        alert("No hay existencias...");
    }
}


