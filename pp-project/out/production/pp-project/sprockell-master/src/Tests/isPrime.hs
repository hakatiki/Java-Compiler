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
    Load (ImmValue 27) regA,
    Store regA (DirAddr 0),
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Load (ImmValue 1) regA,
    Store regA (DirAddr 2),
    Load (DirAddr 1) regA,
    Push regA,
    Load (DirAddr 0) regA,
    Pop regB,
    Compute Lt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 38),
    Load (DirAddr 1) regA,
    Store regA (DirAddr 3),
    Load (DirAddr 3) regA,
    Push regA,
    Load (DirAddr 0) regA,
    Pop regB,
    Compute Lt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 22),
    Load (DirAddr 1) regA,
    Push regA,
    Load (DirAddr 3) regA,
    Pop regB,
    Compute Mul regB regA regA,
    Push regA,
    Load (DirAddr 0) regA,
    Pop regB,
    Compute Equal regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 4),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 2),
    Jump (Rel 1),
    Load (DirAddr 3) regA,
    Push regA,
    Load (ImmValue 1) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 3),
    Jump (Rel  (-27)),
    Load (DirAddr 1) regA,
    Push regA,
    Load (ImmValue 1) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 1),
    Jump (Rel  (-43)),
    Load (DirAddr 2) regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 4),
    Load (ImmValue 1) regA,
    WriteInstr regA numberIO,
    Jump (Rel 3),
    Load (ImmValue 0) regA,
    WriteInstr regA numberIO,
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    EndProg]
main = run [prog]
