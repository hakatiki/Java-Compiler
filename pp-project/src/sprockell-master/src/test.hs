import Sprockell
prog :: [Instruction]
prog = [ --ThreadedDec
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Load (ImmValue 1) regA,
    WriteInstr regA (DirAddr 1),
    Load (ImmValue 2) regA,
    WriteInstr regA (DirAddr 2),
    ReadInstr (IndAddr regSprID),
    Receive regA,                       -- Here all is good still
    Compute Equal regSprID reg0 regB,   -- 10
    Branch regB (Rel 4),
    Compute NEq regA regSprID regB,     -- if 
    Branch regB (Ind regA),             -- Threads > 0 jump -- 15
    Jump (Rel (-6)),

    -- VarDec
    Load (ImmValue 5) regA,             -- variable x
    WriteInstr regA (DirAddr 4),        -- store 18


    -- ThreadedBlock
    ReadInstr (DirAddr 1),
    Receive regA,                       -- 20
    Load (ImmValue 1) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-5)),
    WriteInstr regPC (DirAddr 1),
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 6),
    ReadInstr (DirAddr 4),
    Receive regA,
    WriteInstr regA numberIO,


    WriteInstr regSprID (DirAddr 1),    --31
    Jump (Abs 6),
    -- loop until all done
    ReadInstr (DirAddr 1),
    Receive regA,
    Compute NEq regA regC regB,

    Branch regB (Rel (-3)),
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,      
    Branch regB (Rel 2),
    EndProg,
    WriteInstr regA (DirAddr 1),
    WriteInstr regA (DirAddr 2),
    EndProg]
-- main = runWithDebugger (debuggerSimplePrintAndWait myShow) [prog,prog,prog]
main = run [prog,prog,prog]