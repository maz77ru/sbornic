package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Variant_raspila_3 {
    List kakie_detali=new ArrayList();
    //здесь по порядку беребираем сумму элементов на предмет наименьшего остатка

    int  ostatok_varianta_2(int Ostatok,int[]detali){
        //колличество элементов берем от второго варианта чтобы ограничить количесво перебора
        int min_ostatok=Ostatok;//здесь храним наименьший остаток
        int poradok=1;// храним стартовое значение индекса второго элемента
        for (int i=0;i<detali.length;i++){
            for (int k=poradok; k<detali.length;k++){
                if (k==i){continue;}//нельзя чтобы индексы были одинаковые
               int ost=(Ostatok-(detali[i]+detali[k]));
             if (ost<0){break;} //если остаток отрицательный то дальнейшее вычесление бесмысленно
             else {
                 if (ost<min_ostatok){min_ostatok=ost; // когда все удачно обнавляем параметры 
                 kakie_detali.clear();
               kakie_detali.add(detali[i]);
               kakie_detali.add(detali[k]);
                 }
             }
            }
            poradok++;
        }
        return min_ostatok;
    }

}
