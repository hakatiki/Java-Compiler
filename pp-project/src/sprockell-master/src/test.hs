import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 15 ) regA,
    Store regA (DirAddr 0 ),
    Load (DirAddr 0 ) regA,
    WriteInstr regA numberIO,
    Load (ImmValue 15 ) regA,
    WriteInstr regA numberIO,
    EndProg]
main = run [prog]