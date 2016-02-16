class PayAppHeaders extends React.Component {
  constructor(props) {
    super(props)
  }
  render() {
    return (
      <thead>
        <th>Item No.</th>
        <th>Cost Code</th>
        <th>Description of Work</th>
        <th>Scheduled Value</th>
        <th>Work Completed From Previous Application</th>
        <th>Work Completed From This Period</th>
        <th>Materials Presently Stored</th>
        <th>Total Completed And Stored To Date</th>
        <th>Percent Complete</th>
        <th>Balance To Finish</th>
        <th>From Previous Application Work Retainage</th>
        <th>From Previous Application Materials Retainage</th>
        <th>From Previous Application Total</th>
        <th>Retained This Period Work Retainage Percent</th>
        <th>Retained This Period Work Retainage Dollar</th>
        <th>Retained This Period Materials Retainage Percent</th>
        <th>Retained This Period Materials Retainage Dollar</th>
        <th>Released This Period Work Retainage</th>
        <th>Released This Period Materials Retainage</th>
        <th>Currently Retained Work Retainage</th>
        <th>Currently Retained Materials Retainage</th>
        <th>Currently Retained Total</th>
      </thead>
    )
  }
}

export default PayAppHeaders;
