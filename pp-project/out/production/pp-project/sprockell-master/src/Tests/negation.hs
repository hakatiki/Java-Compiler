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
    Load (ImmValue 0) regA,
    Store regA (DirAddr 0),
    Load (DirAddr 0) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 4),
    Load (ImmValue 200) regA,
    WriteInstr regA numberIO,
    Jump (Rel 1),
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    EndProg]
main = run [prog]
