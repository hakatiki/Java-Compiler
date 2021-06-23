import Sprockell
prog :: [Instruction]
prog = [
	Load (ImmValue 0) regA,
	WriteInstr regA (DirAddr 0),

	TestAndSet (DirAddr 0),
	Receive regD,
	Load (ImmValue 1) regC,
	Compute NEq regD regC regD,
	Branch regD (Rel -4),

	Load (ImmValue 0) regA,
	WriteInstr regA (DirAddr 0),

	EndProg]
main = run [prog]
