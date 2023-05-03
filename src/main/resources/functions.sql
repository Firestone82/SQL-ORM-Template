IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'NewReservation')
    BEGIN
        DROP PROCEDURE NewReservation
    END
GO

CREATE PROCEDURE NewReservation
    @customerID INT,
    @tableID INT,
    @amount INT,
    @startDate DATETIME,
    @endDate DATETIME,
    @description VARCHAR(255),
    @status VARCHAR(100) OUTPUT
AS
BEGIN
    DECLARE @numberOfConflicts INT
    DECLARE @reservationFits VARCHAR(10)

    -- Check for conflicts with other reservations
    SELECT @numberOfConflicts = COUNT(*)
    FROM Reservation
    WHERE tableID = @tableID
      AND (
        (@startDate BETWEEN start AND [end])
        OR (@endDate BETWEEN start AND [end])
        OR (start <= @startDate AND [end] >= @endDate))
      AND canceled IS NULL;

    IF @numberOfConflicts > 0
        BEGIN
            SET @status = 'Table is reserved, for this date.';
            RETURN;
        END

    -- Check if table can accommodate requested amount of people
    SELECT @reservationFits =
           CASE
               WHEN @amount > capacity THEN 'Wont fit'
               ELSE 'Fits'
           END
    FROM [Table]
    WHERE tableID = @tableID;

    IF @reservationFits = 'Wont fit'
        BEGIN
            SET @status = 'Table wont fit that amount of people';
            RETURN;
        END

    -- Insert new reservation
    INSERT INTO Reservation (customerID, tableID, start, [end], amount, created, canceled, description)
    VALUES (@customerID, @tableID, @startDate, @endDate, @amount, GETDATE(), NULL, @description);

    SET @status = 'Reservation created';
END;