import * as React from 'react';
import * as ReactDOM from 'react-dom';

import 'main.css';
import {GoLinkList} from '@src/components/GoLinkList';
import {GoLink} from '@src/GoLinkService';
import {AddGoLink} from '@src/components/AddGoLink';

const goLinks = [
  new GoLink('google', new URL('https://google.com'), new Date()),
  new GoLink('fb', new URL('https://facebook.com'), new Date()),
  new GoLink('rd', new URL('https://reddit.com'), new Date()),
];

const app: React.ReactElement<any> = (
    <div>
      <AddGoLink/>
      <GoLinkList goLinks={goLinks}/>
    </div>
);

ReactDOM.render(app, document.getElementById('app'));
