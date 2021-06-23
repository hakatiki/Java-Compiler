import Sprockell
prog :: [Instruction]
prog = [
	Load (ImmValue 0) regA,
	WriteInstr regA (DirAddr 0),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 1 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 2 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 3 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 4 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 5 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 6 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 7 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 8 ),
	Load (ImmValue 5 ) regA,
	WriteInstr regA (DirAddr 9 ),
	ReadInstr (DirAddr 1),
	Receive regA,
	WriteInstr regA numberIO,
	EndProg]
main = run [prog]
