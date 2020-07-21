import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/main/resources/features",
        glue = "mystepdefs"


)
public class Runner extends AbstractTestNGCucumberTests {


}
