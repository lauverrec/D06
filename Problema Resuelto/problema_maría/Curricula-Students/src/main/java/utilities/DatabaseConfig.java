
package utilities;

public interface DatabaseConfig {

	public final String	PersistenceUnit				= "Curricula";

	public final String	entitySpecificationFilename	= "./src/main/resources/PopulateDatabase.xml";
	public final String	entityMapFilename			= "./src/main/resources/Entities.map";

}
