package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }@Test
    public void saldoOikein(){
        assertEquals(1000, kortti.getSaldo());
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    } @Test
public void SaldoOnAluksiOikein() {   
    assertEquals("Kortilla on rahaa 1000 senttia",  kortti.toString());
}@Test
public void rahanLataaminenToimiiOikein() {
    kortti.lataaRahaa(2500);
    assertEquals("Kortilla on rahaa 3500 senttia", kortti.toString());
}@Test
public void kortinSaldoEiYlitaMaksimiarvoa() {
    kortti.lataaRahaa(20000);
    assertEquals("Kortilla on rahaa 15000 senttia", kortti.toString());
}@Test
public void kortilleEiVoiLadataNegatiivistaSummaa() {
    kortti.lataaRahaa(2500);
    kortti.lataaRahaa(-10);
    assertEquals("Kortilla on rahaa 3500 senttia", kortti.toString());
}
 @Test 
 public void syoEdullisestiVahentaaSaldoaOikein() {
     kortti.syoEdullisesti();
     assertEquals("Kortilla on rahaa 740 senttia", kortti.toString());
 }@Test
public void syoMaukkaastiVahentaaSaldoaOikein() {

    kortti.syoMaukkaasti();

    assertEquals("Kortilla on rahaa 600 senttia", kortti.toString());
}
@Test
public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
    
    kortti.syoMaukkaasti();
    kortti.syoMaukkaasti();
    // nyt kortin saldo on 2
    kortti.syoEdullisesti();

    assertEquals("Kortilla on rahaa 200 senttia", kortti.toString());
}
@Test
public void syoEdullisestiJosSaldo260() {
    
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();    
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();

    assertEquals("Kortilla on rahaa 220 senttia", kortti.toString());
}public void syoMaukkaastiJosSaldo400() {
    
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();
    kortti.syoMaukkaasti();
    kortti.lataaRahaa(300);
    assertEquals("Kortilla on rahaa 0 senttia", kortti.toString());
}
@Test
public void syoMakkaustiEiVieSaldoaNegatiivikseksi(){
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();
    // nyt kortin saldo on 220
    kortti.syoMaukkaasti();

    assertEquals("Kortilla on rahaa 220 senttia", kortti.toString());
}
@ Test 
public void rahanOttoToimiiJosRahaaOnTarpeeksi(){
    kortti.otaRahaa(200);
    assertEquals("Kortilla on rahaa 800 senttia", kortti.toString());
}
@ Test 
public void saldoEiMuutuJosRahatEiRiita(){
    kortti.otaRahaa(20000);
    assertEquals("Kortilla on rahaa 1000 senttia", kortti.toString());
}
 @Test
 public void rahatRiittaa(){
     
     boolean result = kortti.otaRahaa(200);
 assertTrue(result);
 boolean result2 = kortti.otaRahaa(20000);
 assertFalse(result2);
 }
 
}


