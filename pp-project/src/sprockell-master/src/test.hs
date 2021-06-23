import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 0) regA,
    Store regA (DirAddr 0),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 3) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Branch regA (Rel 4),
    Load (DirAddr 0) regA,
    WriteInstr regA numberIO,
    Jump (Rel ( -8)),
    EndProg
    ]
main = run [prog]