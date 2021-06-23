import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Load (ImmValue 5) regA,
    WriteInstr regA (DirAddr 4),
    ReadInstr (DirAddr 4),
    Receive regA,
    WriteInstr regA numberIO,
    EndProg]
main = run [prog]