start transaction;

use `Acme-HackerRank`;

revoke all privileges on `Acme-HackerRank`.* from 'acme-user'@'%';
revoke all privileges on `Acme-HackerRank`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-HackerRank`;

commit;