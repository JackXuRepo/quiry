package cscc01.summer2018.team11.user;

public class Guest extends User {

	public Guest(String userId) {
		// TODO: need to support multiple guests (generate unique id for each)
		super(userId, null, null, null, null, AccessLevel.GUEST, null);
	}

}
