/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ralahtin
 */
public class KassapaateTest {
    Kassapaate kassapaate;
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    kassapaate = new Kassapaate(100000);
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
     public void hello() {
    }  
     @Test
    public void SaldoOnAluksiOikein() {   
    assertEquals(100000, kassapaate.kassassaRahaa());
    }  
    @Test
    public void maukkaitaLounaitaMyyty(){
    assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());    
    }
    @Test
    public void edullisiaLounaitaMyyty(){
    assertEquals(0, kassapaate.edullisiaLounaitaMyyty());    
    }
    @Test
    public void maukkaitaMyytyToimiiOikein(){
        kassapaate.syoMaukkaasti(500);
    assertEquals(100400, kassapaate.kassassaRahaa()); 
    }
    @Test
    public void maukkaitaMyytyToimiiOikein2(){
        kassapaate.syoMaukkaasti(300);
    assertEquals(100000, kassapaate.kassassaRahaa()); 
    }
    
    @Test 
    public void vaihtorahaMaukkaistaOnOikein(){
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }  
    @Test 
    public void vaihtorahaMaukkaistaOnOikein2(){
        assertEquals(300, kassapaate.syoMaukkaasti(300));
    }   

    @Test
    public void maaraMaukkaistaOikein(){
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisiaMyytyToimiiOikein(){
        kassapaate.syoEdullisesti(500);
    assertEquals(100260, kassapaate.kassassaRahaa()); 
    } @Test
    public void edullisiataMyytyToimiiOikein2(){
        kassapaate.syoMaukkaasti(100);
    assertEquals(100000, kassapaate.kassassaRahaa()); 
    }
    @Test 
    public void vaihtorahaEdullisistaOnOikein(){
        assertEquals(240, kassapaate.syoEdullisesti(500));
    }
    @Test 
    public void vaihtorahaEdullisistaOnOikein2(){
        assertEquals(100, kassapaate.syoEdullisesti(100));
    }
     @Test
    public void maaraEdullisistaOikein(){
        kassapaate.syoEdullisesti(260);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

}