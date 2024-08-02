-- V7__CREATE_INDEXES.sql

CREATE INDEX IDX_BOOK_TITLE ON BOOKS(TITLE);
CREATE INDEX IDX_BOOK_AUTHOR_ID ON BOOKS(AUTHOR_ID);

CREATE INDEX IDX_AUTHOR_FIRST_NAME ON AUTHORS(FIRST_NAME);
CREATE INDEX IDX_AUTHOR_LAST_NAME ON AUTHORS(LAST_NAME);

CREATE INDEX IDX_BORROWING_HISTORY_USER_ID ON BORROWING_HISTORY(BORROWER_ID);
CREATE INDEX IDX_BORROWING_HISTORY_BOOK_ID ON BORROWING_HISTORY(BORROWED_BOOK_ID);

CREATE INDEX IDX_BOOK_ID ON BOOKS_CATEGORIES (BOOK_ID);
CREATE INDEX IDX_CATEGORY_ID ON BOOKS_CATEGORIES (CATEGORY_ID);

CREATE INDEX IDX_CATEGORY_GENRE ON CATEGORIES (GENRE);