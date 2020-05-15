
var foto = 'calaveritas';

function mainmethod(){
    var imagen = document.getElementById('calavera');
    if(foto == "calaveritas"){
        imagen.src = "calaveri.jpg";
        foto = "calaveritas";
    }else{
        imagen.src="calaveri.png";
        foto = "calaveri";
    }
}

function cambiar () {
  document.getElementById('craneo').src = "calaveri.jpg"; 
 }
 
 function volver () {
  document.getElementById('craneo').src = "//calaveritas.jpg";
 }
 console.log('hola js');
 
 
 function validar_comensales(){
    var cliente = document.getElementById('comensales');
    cliente > 0 ? true: mensaje = join.document.write("Debes colocar solo numeros");
 }