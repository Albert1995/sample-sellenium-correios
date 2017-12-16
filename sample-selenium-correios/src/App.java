import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class App {

	public static void main(String[] args) {
		// Nota: Para Firefox a partir da versão 47 é necessário uso de um
		// driver extra para o funcionamento correto da biblioteca selenium.
		System.setProperty("webdriver.gecko.driver", "src/geckodriver.exe");
		
		
		// Site do Correios
		String urlCorreios = "http://www.correios.com.br/";
		
		// CEP que será utilizado no site do correios
		String cep = "82130170";
		
		// Instanciando o driver para abrir o navegador
		// Utilizando o Firefox, por ser padrão da biblioteca selenium
		WebDriver driver = new FirefoxDriver();
		
		// Navegando para o site do correios
		driver.get(urlCorreios);
		
		// Procurando o elemento no site que realiza busca de CEPs
		driver.findElement(By.id("acesso-busca")).sendKeys(cep);
		
		// Procurando o elemento para iniciar a busca do CEP digitado
		driver.findElements(By.className("acesso-busca-submit")).get(0).click();
		
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());

		driver.close();
		
		driver.switchTo().window(handles.get(1));
		try { Thread.sleep(5000); } catch (Exception e) {}
		
		if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/p")).getText().equals("DADOS ENCONTRADOS COM SUCESSO.")) {
			System.out.println("Endereço é " + driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[1]")).getText());
		}
		
		// Fechando o driver antes de finalizar o programa
		driver.close();
		driver.quit();
	}

}
