package Generation;

import java.util.LinkedHashMap;
import java.util.Map;

/** Class combining the information of a single scope level. */
public class Scope {
	/** Current size of this scope (in bytes). 
	 * Used to calculate offsets of newly declared variables. */
	private int size;
	private final int INT_SIZE = 4;
	/** Map from declared variables to their types. */
	/** Map from declared variables to their offset within the allocation
	 * record of this scope. */
	private  Map<String, Integer> offsets;

	/** Constructs a fresh, initially empty scope. */
	public Scope() {
		this.offsets = new LinkedHashMap<>();
		this.size = 0;
	}
	public Scope getCopy(){
		Scope scope = new Scope();
		scope.offsets.putAll(this.offsets);
		scope.size = this.size;
		return scope;
	}

	/** Tests if a given identifier is declared in this scope. */

	/** Declares an identifier with a given type, if the identifier
	 * is not yet in this scope.
	 * @return <code>true</code> if the identifier was added;
	 * <code>false</code> if it was already declared.
	 */
	public boolean put(String id) {
		boolean result = !this.offsets.containsKey(id);
		if (result) {
			this.offsets.put(id, this.size);
			this.size += INT_SIZE;
		}
		return result;
	}

	/** Returns the type of a given (presumably declared) identifier.
	 */

	/** Returns the offset of a given (presumably declared) identifier. 
	  * with respect to the beginning of this scope's activation record.
	  * Offsets are assigned in order of declaration. 
	  */
	public Integer address(String id) {
		return this.offsets.get(id);
	}
}