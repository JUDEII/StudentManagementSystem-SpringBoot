-- CREATE TABLE lecturer
IF  NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[lecturer]') AND type in (N'U'))
  BEGIN
    CREATE TABLE [dbo].[lecturer](
      [id] [BIGINT] IDENTITY(1,1) NOT NULL,
      [name] VARCHAR(100),
      [sex] VARCHAR(50),
      [department_id] BIGINT NOT NULL,
      [role] VARCHAR(50),
      [email] VARCHAR(50),
      [created_on] DATETIME2(7)
      CONSTRAINT [PK_payments] PRIMARY KEY CLUSTERED
        (
          [id] ASC
        )
    ) ON [PRIMARY]
  END
GO

