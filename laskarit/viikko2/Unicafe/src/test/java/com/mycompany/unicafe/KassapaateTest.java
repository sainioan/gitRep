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
    Kassapaate kassapaate = new Kassapaate();
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
    @Test 
    public void kortiltaVeroitetaanMaukas(){
        
    Maksukortti kortti = new Maksukortti(1000);
        boolean result = kassapaate.syoMaukkaasti(kortti);
        assertTrue(result);
    }
    @Test 
    public void kortiltaEiVeroitetaMaukas(){
        
    Maksukortti kortti = new Maksukortti(300);
        boolean result = kassapaate.syoMaukkaasti(kortti);
        assertFalse(result);
    }
    @Test
    public void kortiltaVeroitetaanMaukasOikein(){
        
    Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.getSaldo());
    }
    @Test
    public void kortiltaVeroitetaanMaukasOikein2(){
        
    Maksukortti kortti = new Maksukortti(300);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(300, kortti.getSaldo());
    }
    @Test
    public void kortiltaVeroitetaanMaukasMaaraKasavaa(){
        
    Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortiltaVeroitetaanMaukasMaaraEiKasava(){
        
    Maksukortti kortti = new Maksukortti(300);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test 
    public void kortiltaVeroitetaanEdullisesti(){
        
    Maksukortti kortti = new Maksukortti(1000);
        boolean result = kassapaate.syoEdullisesti(kortti);
        assertTrue(result);
    }
       @Test 
    public void kortiltaEiVeroitetaEdullinen(){
        
    Maksukortti kortti = new Maksukortti(200);
        boolean result = kassapaate.syoEdullisesti(kortti);
        assertFalse(result);
    }
    @Test
    public void kortilatVeroitetaanEdullinenOikein(){
        
    Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(740, kortti.getSaldo());
    }
    @Test
    public void kortilatVeroitetaanEdullinenOikein2(){
        
    Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(200, kortti.getSaldo());
    
    }  
    @Test
    public void kortiltaVeroitetaanEdullinenMaaraKasvaa(){
        
    Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortiltaVeroitetaanEdullinenMaaraEiKasva(){
        
    Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortiltaVeroitetaanMaukasKassanRahaMaaraEiKasva(){
        
    Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void kortiltaVeroitetaanEdullinenKassanRahaMaaraEiKasva(){
        
    Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test 
    public void latausToimiiOikein(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassapaate.kassassaRahaa());
    }
    @Test 
    public void latausToimiiOikein2(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.lataaRahaaKortille(kortti, -500);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }


}