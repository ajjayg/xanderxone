// Adding owner column to store the user that worked on this offline negotiation
ALTER TABLE negotiation_details ADD owner text;

// Adding reply_time column to store the time stamp when admin user replied to this offline negotiation
ALTER TABLE negotiation_details ADD reply_time timestamp;

