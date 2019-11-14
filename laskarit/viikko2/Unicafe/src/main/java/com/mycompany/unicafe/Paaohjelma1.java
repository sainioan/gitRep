package com.mycompany.unicafe;

public class Paaohjelma1 {

    public static void main(String[] args) {
        Kassapaate unicafeExactum = new Kassapaate();
        Maksukortti kortti = new Maksukortti(100);
        
        unicafeExactum.syoEdullisesti(kortti);
        
        System.out.println("Lounaita myyty:" + unicafeExactum.edullisiaLounaitaMyyty() );
        System.out.println(kortti);
    }
}
