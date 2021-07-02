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
    Load (ImmValue 12) regA,
    Store regA (DirAddr 0),
    Load (ImmValue 24) regA,
    Store regA (DirAddr 1),
    Load (ImmValue 24) regA,
    Store regA (DirAddr 2),
    Load (ImmValue 2) regA,
    Load (ImmValue 0) regE,
    Compute Add regA regE regE,
    Load (ImmValue 36) regA,
    Store regA (IndAddr regE),
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    EndProg]
main = run [prog]
