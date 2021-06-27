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
    Load (ImmValue 20) regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (ImmValue 1 ) regA,
    Store regA (DirAddr 2),
    Load (DirAddr 1) regA,
    Push regA,
    Load (ImmValue 5) regA,
    Pop regB,
    Compute Lt regB regA regA,
    Load (ImmValue 0) regE,
    Compute Equal regE regA regA,
    Branch regA (Rel 12),
    Load (ImmValue 1) regA,
    Store regA (DirAddr 0),
    Load (DirAddr 1) regA,
    Push regA,
    Load (DirAddr 0) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    WriteInstr regA numberIO,
    Jump (Rel  (-18)),
    Load (DirAddr 0) regA,
    WriteInstr regA numberIO,
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    EndProg]
main = run [prog]