package content;

/**
 * @author Manuel Gallina
 */

public class Patient 
{
	private static final String[] DEFAULT_ATTRIBUTES = {"Età", "Peso"};
	private static final String[] DEFAULT_INFO = {"def", "def"};
	
	private String[] patientAttributes;		//Labels
	private String[] patientInfo;			//Data
	
	/**
	 * @return the patient attributes
	 */
	public String[] getPatientAttributes() {
		return patientAttributes;
	}
	/**
	 * @param patientAttributes the patient attributes to set
	 */
	public void setPatientAttributes(String[] patientAttributes) {
		this.patientAttributes = patientAttributes;
	}

	/**
	 * @return the patient informations
	 */
	public String[] getPatientInfo() {
		return patientInfo;
	}
	/**
	 * @param patientInfo the patient informations to set
	 */
	public void setPatientInfo(String[] patientInfo) {
		this.patientInfo = patientInfo;
	}
	
	/**
	 * Default constructor for the Patient class
	 */
	public Patient()
	{
		this.setPatientAttributes(DEFAULT_ATTRIBUTES);
		this.setPatientInfo(DEFAULT_INFO);
	}
	
	/**
	 * Constructor for the Patient class
	 * 
	 * @param _patientAttributes the list of attribute's labels
	 * @param _patientInfo the list of information data. The informations should be in the appropriate order to be correctly displayed 
	 */
	public Patient(String[] _patientAttributes, String[] _patientInfo)
	{
		this.setPatientAttributes(_patientAttributes);
		this.setPatientInfo(_patientInfo);
	}
}
