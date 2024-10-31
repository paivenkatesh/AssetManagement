CREATE DATABASE asset_management;
USE asset_management;

-- Employee table created
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'User') NOT NULL
);

-- Asset table created
CREATE TABLE asset (
    asset_id INT AUTO_INCREMENT PRIMARY KEY,
    asset_name VARCHAR(100) NOT NULL,
    asset_category VARCHAR(50) NOT NULL,
    asset_model VARCHAR(50),
    manufacturing_date DATE,
    expiry_date DATE,
    asset_value DECIMAL(10, 2) NOT NULL,
    status ENUM('Available', 'Borrowed') DEFAULT 'Available'
);

-- Asset_Borrowing table created
CREATE TABLE asset_borrowing (
    borrowing_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    asset_id INT,
    borrowed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    returned_at TIMESTAMP,
    status ENUM('Active', 'Returned') DEFAULT 'Active',
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (asset_id) REFERENCES asset(asset_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Service_Request table created
CREATE TABLE service_request (
    service_request_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    asset_id INT,
    description TEXT NOT NULL,
    issue_type ENUM('Hardware Failure', 'Software Issue', 'Routine Maintenance') NOT NULL,
    status ENUM('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (asset_id) REFERENCES asset(asset_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Asset_Audit table created
CREATE TABLE asset_audit (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    asset_id INT,
    audit_status ENUM('Pending', 'Verified', 'Rejected') DEFAULT 'Pending',
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (asset_id) REFERENCES asset(asset_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);