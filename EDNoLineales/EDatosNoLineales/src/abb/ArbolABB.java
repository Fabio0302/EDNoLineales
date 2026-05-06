/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abb;

/**
 *
 * @author jorge.reyes
 */
public class ArbolABB {
    protected NodoBin raiz;
    
    public ArbolABB(Object o){
        raiz = new NodoBin(o);
    }
    
    public ArbolABB(){
        this(null);
    }
    
    public void posOrden(){
        if (raiz!=null)
            raiz.posOrden();
    }
    
    public void preOrden(){
        if (raiz!=null)
            raiz.preOrden();
    }
    
    public void inOrden(){
        if (raiz!=null)
            raiz.inOrden();
        }
    
    public void insertar(Object o){
        insertarOrdenado(raiz,o);
    }
    
    public void insertarOrdenado(NodoBin n, Object o){
        if ((int)o<(int)n.getDato()){
            if (n.getIzq()==null)
                n.setIzq(new NodoBin(o));
            else
                insertarOrdenado(n.getIzq(),o);
        }
        else{
            if ((int)o>(int)n.getDato())
                if (n.getDer()==null)
                    n.setDer(new NodoBin(o));
                else
                    insertarOrdenado(n.getDer(),o);
        }
    }
    
    public void buscar(Object o){
        buscar(raiz,o);
    }
    
    public void buscar(NodoBin n, Object o) throws ItemNotFoundException{
        if ((int)o<(int)n.getDato()){
            if (n.getIzq()==null)
                throw new ItemNotFoundException("El elemento no se "
                        + "encuentra");
            else
                buscar(n.getIzq(),o);
        }
        else{
            if ((int)o>(int)n.getDato()){
                if (n.getDer()==null)
                    throw new ItemNotFoundException("El elemento no se "
                            + "encuentra");
                else
                    buscar(n.getDer(),o);
            }
            else
                System.out.println("El elemento sí se encuentra "
                        + "en el árbol");
        }
    }
    
    public void borrar(Object o){
        borrar(raiz,o);
    }
    
    public NodoBin borrar(NodoBin n, Object o) throws ItemNotFoundException{
    if (n==null)
        throw new ItemNotFoundException("Elemento no encontrado");
    else{
        if ((int)o>(int)n.getDato())
            n.setDer(borrar(n.getDer(),o));
        else{
            if ((int)o<(int)n.getDato())
                n.setIzq(borrar(n.getIzq(),o));
            else{
                if (n.getDer()!=null && n.getIzq()!=null) //Aplicar el criterio 3a o 3b
                {//Criterio del hijo más a la izquierda del subárbol derecho.
                    NodoBin minimo = buscarMin(n.getDer());
                    n.setDato(minimo.getDato());
                    n.setDer(borrarMin(n.getDer()));
                }
                else
                    n = (n.getIzq()!=null)? n.getIzq():n.getDer();
            }
        }
    }
    return n;
    }
    
    
    public NodoBin buscarMin(NodoBin n){
        while(n.getIzq()!=null)
            n = n.getIzq();
        return n;
    }
    
    public NodoBin borrarMin(NodoBin n){
        if (n.getIzq()!=null){
            n.setIzq(borrarMin(n.getIzq()));
            return n;
        }
        else 
            return n.getDer();
    }
    
    public static void main(String[] args) {
        ArbolABB arbol = new ArbolABB(120);
        arbol.insertar(87);
        arbol.insertar(43);
        arbol.insertar(65);
        arbol.insertar(140);
        arbol.insertar(99);
        arbol.insertar(130);
        arbol.insertar(22);
        arbol.insertar(56);
        System.out.println("Recorrido en InOrden");
        arbol.inOrden();
        System.out.println("Recorrido en PosOrden");
        arbol.posOrden();
        System.out.println("Recorrido en PreOrden");
        arbol.preOrden();
        System.out.println("Buscando elementos...");
        try{
        arbol.buscar(1);
        }
        catch(ItemNotFoundException e){
            System.out.println("Error " + e.getMessage());
        }
        
        System.out.println("Borrando un elemento del árbol");
        try{
            arbol.borrar(140);
            arbol.inOrden();
        }
        catch (ItemNotFoundException e){
            System.out.println("Elemento no encontrado");
        }
    }
}
