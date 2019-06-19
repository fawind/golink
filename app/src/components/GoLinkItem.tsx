import * as React from 'react';
import {ReactElement} from 'react';
import {GoLink} from '@src/GoLinkService';

interface Props {
  goLink: GoLink,
}

export const GoLinkItem: React.FunctionComponent<Props> = (props: Props): ReactElement => {
  return (
      <li>
        <b>{props.goLink.alias}: </b>
        <a href={props.goLink.url.toString()}>{props.goLink.url.toString()}</a>
      </li>
  );
};
