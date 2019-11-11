/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class MaksukorttiTest {
 Maksukortti kortti;    
    public MaksukorttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @After
    public void tearDown() {
    }

//     TODO add test methods here.
//     The methods must be annotated with annotation @Test. For example:
//    
     @Test
     public void hello() {
     }
     
 @Test
public void konstruktoriAsettaaSaldonOikein() {   
    assertEquals("Kortilla on rahaa 10.0 euroa",  kortti.toString());
}
 @Test 
 public void syoEdullisestiVahentaaSaldoaOikein() {
     kortti.syoEdullisesti();
     assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
 }@Test
public void syoMaukkaastiVahentaaSaldoaOikein() {

    kortti.syoMaukkaasti();

    assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
}
@Test
public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
    
    kortti.syoMaukkaasti();
    kortti.syoMaukkaasti();
    // nyt kortin saldo on 2
    kortti.syoEdullisesti();

    assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
}
@Test
public void syoEdullisestiJosSaldo2_50() {
    
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();    
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();

    assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
}public void syoMaukkaastiJosSaldo4_00() {
    
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();
    kortti.syoMaukkaasti();
    kortti.lataaRahaa(3.0);
    assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
}
@Test
public void syoMakkaustiEiVieSaldoaNegatiivikseksi(){
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();
    kortti.syoEdullisesti();
    
    kortti.syoMaukkaasti();

    assertEquals("Kortilla on rahaa 2.5 euroa", kortti.toString());
}

@Test
public void kortilleVoiLadataRahaa() {
    kortti.lataaRahaa(25);
    assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
}
@Test
public void kortilleEiVoiLadataNegatiivistaSummaa() {
    kortti.lataaRahaa(25);
    kortti.lataaRahaa(-10);
    assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
}

@Test
public void kortinSaldoEiYlitaMaksimiarvoa() {
    kortti.lataaRahaa(200);
    assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
}



}
