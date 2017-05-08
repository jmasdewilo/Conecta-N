/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectan;

/**
 *
 * @author José María Serrano
 * @version 1.1 Departamento de Informática Universidad de Jáen
 *
 * Inteligencia Artificial 2º Curso Grado en Ingeniería Informática
 *
 */
public class JugadorCPUAlfaBeta extends JugadorCPU {

    private Object grid;
 
    @Override
    public int jugada(int x, int y, int conecta, Tablero grid) {
        int nTablero[][]=grid.toArray();
        
        int val=Integer.MAX_VALUE;
        
        int posicion = genRandom(x, grid);
        
        System.out.println(grid.getAlto());
        System.out.println(grid.getAncho());
        
        
        for (int i = 0; i < 8; i++) {
            if (nTablero[0][i] == 0) {
                for (int j = 7; j >= 0; j--) {
                    if (nTablero[j][i] == 0) {
                        nTablero[j][i] = 2;

                        int val_aux = minMax(grid.toArray());
                        if (val_aux < val) {
                            val = val_aux;
                            posicion = i;
                            System.out.println(i);
                        }

                        nTablero[j][i] = 0;
                        break;
                    }
                }
            }
        }
        
        
        
        int k = grid.getAlto();
        //Ir a la última posición de la columna	
        do {
            k--;
        } while (grid.getButton(k, posicion).getIcon() != null & k != 0);
        //Pintar Ficha
        grid.setButton(k, posicion, false);
        return grid.checkWin(k, posicion, conecta);
    
    }
     
    
    private int minMax(int [][] tabl){
             
        int f=0, depth=0;
        int v=Integer.MIN_VALUE;
        int aux;
        for (int n=0;n<8;n++){
            for (int m=0;m<14;m++){
                if (tabl[n][m]==-1){
                    tabl[n][m]=1;
                    aux=min(tabl);
                    if (aux>v) {
                        v=aux;
                        f=n;
                        depth=m;
                    }
                    tabl[n][m]=-1;
                }
            }
        }
        tabl[f][depth]=1;
        return v;
    }
    
        private int max(int [][] tabl){
            int v=Integer.MIN_VALUE;
            int aux;
            for (int n=0;n<8;n++){
                for (int m=0;m<14;m++){
                    if (tabl[n][m]==-1){
                        tabl[n][m]=1;
                        aux=min(tabl);
                        if (aux>v) v=aux;
                        tabl[n][m]=-1;

                    }
                }
            }
            return v;
        }

        private int min(int [][] tabl){
            int v=Integer.MAX_VALUE;
            int aux;
            for (int n=0;n<8;n++){
                for (int m=0;m<14;m++){
                    if (tabl[n][m]==-1){
                        tabl[n][m]=0;
                        aux=max(tabl);
                        if (aux<v) v=aux;
                        tabl[n][m]=-1;
                    }
                }
            }
            return v;
        }
}
    

