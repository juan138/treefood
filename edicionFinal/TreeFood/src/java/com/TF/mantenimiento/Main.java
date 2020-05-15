/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.mantenimiento;

import com.TF.modeloProceso.InsertarCliente;
import com.TF.persistencia.Usuarios;

/**
 *
 * @author juan.serranoUSAM
 */
public class Main {
    
   
    
    // main usado solo para prueba y error
    public static void main(String[] args) {
        ValidacionUsuarios vu = new ValidacionUsuarios();
      //  ControlSucMunicipios mun = new ControlSucMunicipios();
       // Usuarios us = new Usuarios();
        InsertarCliente cli = new InsertarCliente();
       /* 
        us.setNickname("js");
        us.setPass("123");
        ValidarView user = vu.validarUsuario(us);
        
        System.out.println("dato: "+user.getNickname());
        */
       
        /*
        System.out.println("id departamento      municipio");
        for(int i = 0 ; i< mun.consultarDepartamentos().size();i++){
            for(int n = 0 ; n< mun.consultarDepartamentos().get(i).getMunicipiosList().size();n++){
                System.out.println(""+mun.consultarDepartamentos().get(i).getIdDepartamento()+
                        "  | "+ mun.consultarDepartamentos().get(i).getDepartamento()+
                        "   |  "+mun.consultarDepartamentos().get(i).getMunicipiosList().get(n).getMunicipio());
            }
        }
        
        System.out.println("dato: "+user.getIdPersona().getIdTipoPersona().getTipoPer());
        */
        
/*
       
         cli.setUsua("jp");
         cli.setPass("312");
         cli.setNombre("joel");
         cli.setApellido("perez");
         cli.setEdad(28);
         cli.setTp(1);
         cli.setCorreo("tp23@hotmail.com");
         cli.setTel("7585641");

         boolean resp = vu.nuevoCliente(cli);
         System.out.println("respuesta: "+resp); 
     */    


/*
      
        boolean res = vu.correoCliente("tp23@hotmail.com");
        System.out.println("correo existe: "+ resp);
       */
      /*
      
         cli.setIdper(10);
         cli.setNombre("joel");
         cli.setApellido("lopes");
         cli.setEdad(28);
         cli.setCorreo("tp23@hotmail.com");
         cli.setTel("7585641");

         boolean resp = vu.updateCliente(cli);
         System.out.println("respuesta: "+resp); 
     */
      /*
        String msg="";
        Usuarios user = new Usuarios();
        
       user = vu.validarUsuario(us);
        System.out.println("dato: "+user.getNickname());
        if(us == null){
            msg="usuario no existe";
        }else{
            msg="usuario "+user.getNickname();
        }
    */
    }
}
