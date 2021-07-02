package Generation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// ----- Class representing Scope -----
public class Scope {


    // ======== Variables ========
	private int localSize;                  //localSize and sharedSize -> Vars store "how full" the memory blocks are
	private int sharedSize;
	private final int INT_SIZE = 1;
	private Map<String, Integer> offsets;   //saves offsets in memory of a variable ID
	private Map<String, Boolean> shared;    //saves whether variable in ID is in shared memory or not


	// ======== Constructor ========
    public Scope() {
        this.offsets = new LinkedHashMap<>();
        this.shared = new LinkedHashMap<>();
        this.localSize = 0;
        this.sharedSize = 0;
    }


    // ======== Methods =======
	public Scope getCopy(){ //creates copy so new variables don't get put into parent scope
		Scope scope = new Scope();
		scope.offsets.putAll(this.offsets);
		scope.shared.putAll(this.shared);
		scope.localSize = this.localSize;
		scope.sharedSize = this.sharedSize;
		return scope;
	}

    /**
     *
     * @param id: String -> ID node in ParseTree
     * @param isShared: Boolean -> true if var is in Shared memory, otherwise = false
     * @throws MemoryOutOfBoundsException
     */
	public void put(String id, boolean isShared) throws MemoryOutOfBoundsException { //saves variable offset into HashMap
        if (isShared) {
            this.offsets.put(id, this.sharedSize);
            this.sharedSize += INT_SIZE;
            if (this.sharedSize > 8) {
                throw new MemoryOutOfBoundsException("Shared memory size has exceeded limit 8!");
            }
        } else {
            this.offsets.put(id, this.localSize);
            this.localSize += INT_SIZE;
            if (this.localSize > 32) {
                throw new MemoryOutOfBoundsException("Local memory size has exceeded limit 32!");
            }
        }
	}

	public void putShared(String id, boolean isShared) { //saves variable sharedState into HashMap
	    this.shared.put(id,isShared);
    }

    public Integer address(String id) { return this.offsets.get(id); } //returns offset of ID in HashMap

    public boolean getShared(String id) { return this.shared.get(id); } //returns sharedState of ID in HashMap

	public Map<String,Integer> getOffsets() { return offsets; } //returns HashMap containing offsets

    public void clearLocal() { this.localSize = 0; }

}