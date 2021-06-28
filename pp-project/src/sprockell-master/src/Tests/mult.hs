import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    ReadInstr (IndAddr regSprID),
    Receive regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 4),
    Compute NEq regA regSprID regB,
    Branch regB (Ind regA),
    Jump (Rel (-6)),
    Load (ImmValue 5) regA,
    Store regA (DirAddr 0),
    Load (ImmValue 5) regA,
    Push regA,
    Load (ImmValue 8) regA,
    Pop regB,
    Compute Mul regB regA regA,
    Store regA (DirAddr 0),
    Load (DirAddr 0) regA,
    WriteInstr regA numberIO,
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    EndProg]
main = run [prog]
