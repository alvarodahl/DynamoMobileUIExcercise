package com.dynm.ejercicio.ml;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dynm.base.LogicaBase;

public class OfertasRelampagoPrice extends LogicaBase {

	List<WebElement> lwe;

	@BeforeClass
	public void setUp() {
		setUpBrowser();
	}

	@Test
	@Parameters({ "url" })
	public void a_NavigateToUrl(String url) throws InterruptedException {

		driver.get(url);

		/*
		 * Seleccionar segun parametro la opcion de navegacion. En este caso con
		 * hardcodearla estamos, pero el potencial es poder determinar segun la opcion
		 * que le damos a donde queremos que navegue, sin tener que modificar el codigo,
		 * agregando parameter en TESTNG.XML
		 */

		lwe = driver.findElements(By.className("nav-menu-item-link"));
		for (int i = 0; i < lwe.size(); i++) {
			String navOption = lwe.get(i).getAttribute("innerHTML");
			if (navOption.equalsIgnoreCase("Ofertas")) {
				lwe.get(i).click();
				break;
			}
		}

		/*
		 * El DOM es tan idiota que no importa que parametro le diga que contemple para
		 * la espera, hace cualquiera. Asi que Thread Sleep para esperar que cargue bien
		 * la pagina. Intente con JS y tampoco. Si, conozco Fluent, Explicit o Implicit,
		 * no funcionan para esto feel free to try
		 */

		Thread.sleep(1000);

		lwe = driver.findElements(By.className("carousel_item"));
		for (int i = 0; i < lwe.size(); i++) {
			String navOption = lwe.get(i).getAttribute("innerText");
			if (i == 6) {
				WebElement nextButtonC = driver.findElement(By.cssSelector(".next-button"));
				nextButtonC.click();
			}
			if (navOption.equalsIgnoreCase("Parlantes")) {

				/*
				 * Aca te uso un explicit, que si funciona porque tiene el elemento cargado pero
				 * no clickeable, thus..
				 */

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(lwe.get(i)));
				Actions act = new Actions(driver);
				act.moveToElement(lwe.get(i)).click().build().perform();
				break;
			}
		}
	}

	@Test
	public void b_CompararPrecio() {

		// Screen al listado de items
		takeScreenshot("OfertasRelampago", "Comparar-Precio1");

		// Ingreso al item deseado, en este caso el primero
		lwe = driver.findElements(By.cssSelector(".promotion-item__container"));
		lwe.get(0).click();

		// Segunda Captura Comparativa
		takeScreenshot("OfertasRelampago", "Comparar-Precio2");

		/*
		 * De vuelta, una solucion pragmatica al ejercicio. Bajo un ambiente mas
		 * controlado se podria trabajar distinto Pero teniendo en cuenta que no tenemos
		 * control sobre ML, esta manera es la que cumple.
		 */

	}

	@AfterClass
	public void q_QUIT() {
		driver.quit();
	}

}
