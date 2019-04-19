package game;

public enum InstructionsAsset {
	CL_INSTRUCTIONS("CL_INSTRUCTIONS"),
	RK_INSTRUCTIONS("RK_INSTRUCTIONS"),
	INSTRUCTIONS_LABEL("INSTRUCTIONS_LABEL");

	private String asset_key = null;

	private InstructionsAsset(String s){
		asset_key=s;
	}
}
