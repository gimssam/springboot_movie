CREATE TABLE IF NOT EXISTS movietbl (
    m_code int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    m_name varchar(100) NOT NULL,
    m_director varchar(50) NOT NULL,
    m_nation varchar(50) NOT NULL,
    m_genre varchar(100) NOT NULL,
    m_actor varchar(100) NOT NULL,
    m_open varchar(10) NOT NULL,
    m_synopsis varchar(2000) DEFAULT NULL,
    p_sysname varchar(50) DEFAULT NULL
);

CREATE OR REPLACE VIEW mlist AS
SELECT * FROM movietbl
ORDER BY m_code DESC;
