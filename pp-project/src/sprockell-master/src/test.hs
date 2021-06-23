import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 0 ) regA,
    Store regA (DirAddr 0 ),
    Load (DirAddr 0 ) regA,
    Push regA,
    Load (ImmValue 3 ) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Branch regA (Rel 9),
    Load (DirAddr 0 ) regA,
    Push regA,
    Load (ImmValue 1 ) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA numberIO,
    Store regA (DirAddr 0 ),
    Jump (Rel ( -13)),
    EndProg
    ]
main = run [prog]