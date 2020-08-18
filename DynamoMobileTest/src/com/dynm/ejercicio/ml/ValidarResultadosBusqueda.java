package com.dynm.ejercicio.ml;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dynm.base.LogicaBase;


public class ValidarResultadosBusqueda extends LogicaBase{
	
	
	@BeforeClass
	public void setUp(){
		setUpBrowser();	
	}
	
	@Test
	@Parameters({ "url" })
	public void a_NavigateToUrl(String url) {
		driver.navigate().to(url);
	}

	@Test
	@Parameters({ "busqueda" })
	public void b_SearchML(String busqueda) {

		// Busqueda
		WebElement buscador = driver.findElement(By.name("as_word"));
		buscador.sendKeys(busqueda + Keys.ENTER);

		//Cada cuanto a ML se le ocurre te tira un popup, que a veces lo maneja a veces no... y es un bardo. Si esto no funciona, re-run.
		try{
			WebElement popUp = driver.findElement(By.cssSelector(".andes-tooltip-button-close"));
			popUp.click();
		}catch(NoSuchElementException nse){
			System.out.println("no ta");
		}
		
		// Numero de resultados
		takeScreenshot("ValidarResultadosBusqueda", "b_SearchML");
		
		/*Se analizo buscar la variable de resultados y mostrarla por consola tambien, pero fue imposible dado a que ML cambia de nombre sus clases
		 * constantemente, y no existe ningun elemento unico en la hierarchy. Por ende la solucion mas proxima en eficacia y rapida de sacar fue 
		 * tomar una captura*/
	}

	@AfterClass
	public void q_QUIT() {
		driver.quit();
	}

}
